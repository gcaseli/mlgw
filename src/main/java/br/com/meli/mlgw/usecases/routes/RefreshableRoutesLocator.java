package br.com.meli.mlgw.usecases.routes;

import br.com.meli.mlgw.entities.RouteML;
import br.com.meli.mlgw.externals.database.RouteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

/**
 * Classe responsável por atualizar as rotas de forma dinamica em tempo de execução.
 */
@Component
public class RefreshableRoutesLocator  implements RouteLocator {

  private final RouteLocatorBuilder builder;
  private final GatewayRoutesRefresher gatewayRoutesRefresher;

  private RouteLocatorBuilder.Builder routesBuilder;
  private Flux<Route> route;

  private final RouteRepository routeRepository;

  @Value("${httpbin}")
  private String httpbin;

  @Autowired
  public RefreshableRoutesLocator(final RouteLocatorBuilder builder,
      final GatewayRoutesRefresher gatewayRoutesRefresher,
      final RouteRepository routeRepository) {
    this.builder = builder;
    this.gatewayRoutesRefresher = gatewayRoutesRefresher;
    this.routeRepository = routeRepository;

    clearRoutes();
  }

  public void clearRoutes() {
    routesBuilder = builder.routes();
  }

  public void buildRoutes() {
    this.route = routesBuilder.build().getRoutes();
    gatewayRoutesRefresher.refreshRoutes();
  }

  public void addRoute() {

    List<RouteML> routesDatabase = routeRepository.retrieveRoutes();

    for (RouteML r : routesDatabase) {
      routesBuilder
          .route(p -> p
              .path(r.getPathDestination())
              .uri(httpbin));
    }

  }

  @Override
  public Flux<Route> getRoutes() {
    if (route == null){
      clearRoutes();
      addRoute();
      route = routesBuilder.build().getRoutes();
    }
    return route;
  }
}
