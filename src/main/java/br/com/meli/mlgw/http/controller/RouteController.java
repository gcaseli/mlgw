package br.com.meli.mlgw.http.controller;

import br.com.meli.mlgw.entities.RouteML;
import br.com.meli.mlgw.usecases.routes.RouteUCService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * Classe responsável por receber as chamadas REST
 */
@RestController
@RequestMapping("/api/routes")
@Tag(name = "routes", description = "API para Rotas")
public class RouteController {

  @Autowired
  private RouteUCService routesUCService;

  @Autowired
  private RouteLocatorBuilder routeLocatorBuilder;

  @Operation(description = "Apresenta todas as rotas")
  @GetMapping("/")
  public List<RouteML> findAllRoutes() {
    return routesUCService.retrieveRoutes();
  }

  @DeleteMapping("/{id}")
  public Mono<ResponseEntity<Object>> delete(@PathVariable String id) {
    return this.routesUCService.delete(Mono.just(id))
        .then(Mono.defer(() -> Mono.just(ResponseEntity.ok().build())))
        .onErrorResume(t -> t instanceof NotFoundException,
            t -> Mono.just(ResponseEntity.notFound().build()));
  }

  @GetMapping("/{id}")
  public Mono<ResponseEntity<Object>> retrieveRoute(@PathVariable String id) {
    return this.routesUCService.retrieveRoute(Mono.just(id))
        .flatMap(route -> Mono.just(ResponseEntity.ok().body(route))
        .onErrorResume(t -> t instanceof NotFoundException,
            t -> Mono.just(ResponseEntity.notFound().build())));
  }

  @Operation(description = "Cria uma nova rota")
  @ApiResponse(responseCode = "201", description = "Rota criada")
  @ResponseStatus(HttpStatus.OK)
  @PostMapping(value = "/")
  public ResponseEntity<RouteML> createNewRoute(@Valid @RequestBody RouteRequest request) {

    if (request == null || request.getPathDestination() == null) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    RouteML newRouteML = routesUCService.createNewRoute(RouteML
        .createNewRoute(request.getPathDestination(), request.getOriginIp(),
            request.getMaxRequestPerSecond()));

    routesUCService.updateGatewayRoutes();

    return new ResponseEntity<>(newRouteML, HttpStatus.CREATED);

  }
}
