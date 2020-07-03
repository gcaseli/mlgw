package br.com.meli.mlgw.usecases.routes;

import br.com.meli.mlgw.entities.RouteML;
import java.util.List;
import reactor.core.publisher.Mono;

public interface RouteUCService {

  List<RouteML> retrieveRoutes();

  RouteML createNewRoute(RouteML routeML);

  void updateGatewayRoutes();

  Mono<Void> delete(Mono<String> routeId);

  Mono<Object> retrieveRoute(Mono<String> routeId);
}
