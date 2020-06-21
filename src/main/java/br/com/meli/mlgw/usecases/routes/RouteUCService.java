package br.com.meli.mlgw.usecases.routes;

import br.com.meli.mlgw.entities.Route;
import java.util.List;

public interface RouteUCService {

  List<Route> retrieveRoutes();
}
