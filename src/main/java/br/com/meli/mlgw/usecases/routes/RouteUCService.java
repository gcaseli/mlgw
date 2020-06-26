package br.com.meli.mlgw.usecases.routes;

import br.com.meli.mlgw.entities.RouteML;
import java.util.List;

public interface RouteUCService {

  List<RouteML> retrieveRoutes();

  RouteML createNewRoute(RouteML routeML);

  void updateGatewayRoutes();
}
