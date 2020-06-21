package br.com.meli.mlgw.usecases.routes;

import br.com.meli.mlgw.entities.Route;
import br.com.meli.mlgw.externals.database.RouteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RouteUCServiceImpl implements RouteUCService {

  @Autowired
  private RouteRepository routeRepository;

  @Override
  public List<Route> retrieveRoutes(){
    return routeRepository.retrieveRoutes();
  }
}
