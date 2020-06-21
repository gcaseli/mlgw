package br.com.meli.mlgw.externals.database;

import br.com.meli.mlgw.entities.Routes;
import java.util.List;

public interface RouteRepository {

  List<Routes> retrieveRoutes();
}
