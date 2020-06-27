package br.com.meli.mlgw.usecases.routes;

import br.com.meli.mlgw.entities.RouteML;
import br.com.meli.mlgw.externals.database.RouteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.stereotype.Service;

/**
 * Classe responsável para casos de uso referente a rotas como busca, criação e atualização
 */
@Service
public class RouteUCServiceImpl implements RouteUCService {

  @Autowired
  private RefreshableRoutesLocator routesLocator;

  @Autowired
  private RouteRepository routeRepository;

  @Autowired
  private RouteLocatorBuilder routeLocatorBuilder;

  @Value("${httpbin}")
  private String httpbin;

  @Override
  public List<RouteML> retrieveRoutes(){
    return routeRepository.retrieveRoutes();
  }

  @Override
  public RouteML createNewRoute(RouteML routeML) {
    return routeRepository.createNewRoute(routeML);
  }

  public void updateGatewayRoutes() {
    routesLocator.clearRoutes();
    routesLocator.addRoute();
    routesLocator.buildRoutes();
  }

}
