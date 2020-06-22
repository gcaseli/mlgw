package br.com.meli.mlgw.externals.database;

import br.com.meli.mlgw.entities.Route;
import java.util.List;

public interface RouteRepository {

  List<Route> retrieveRoutes();

  Route createNewRoute(Route route);
}
