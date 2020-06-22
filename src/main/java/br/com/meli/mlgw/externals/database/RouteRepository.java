package br.com.meli.mlgw.externals.database;

import br.com.meli.mlgw.entities.RouteML;
import java.util.List;

public interface RouteRepository {

  List<RouteML> retrieveRoutes();

  RouteML createNewRoute(RouteML routeML);
}
