package br.com.meli.mlgw.usecases.routes;

import br.com.meli.mlgw.entities.RouteML;
import br.com.meli.mlgw.externals.database.RouteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RouteUCServiceImpl implements RouteUCService {

  @Autowired
  private GatewayRoutesRefresher gatewayRoutesRefresher;

  @Autowired
  private RouteRepository routeRepository;

  @Autowired
  private GatewayRoutesRefresher refreshableRoutesUC;

  @Override
  public List<RouteML> retrieveRoutes(){
    return routeRepository.retrieveRoutes();
  }

  @Override
  public RouteML createNewRoute(RouteML routeML) {
    return routeRepository.createNewRoute(routeML);
  }

  /*public void buildRoutes() {

    //routeLocator.getRoutes()

    refreshableRoutesUC.refreshRoutes();
  }*/
}
