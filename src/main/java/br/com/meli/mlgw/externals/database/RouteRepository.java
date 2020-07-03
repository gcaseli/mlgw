package br.com.meli.mlgw.externals.database;

import br.com.meli.mlgw.entities.RouteML;
import java.util.List;
import reactor.core.publisher.Mono;

public interface RouteRepository {

  List<RouteML> retrieveRoutes();

  RouteML createNewRoute(RouteML routeML);

  int delete(String routeId);

  Mono<Object> retrieveRoute(Mono<String> routeId);
}
