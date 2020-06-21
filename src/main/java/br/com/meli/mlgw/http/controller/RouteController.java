package br.com.meli.mlgw.http.controller;

import br.com.meli.mlgw.entities.Route;
import br.com.meli.mlgw.usecases.routes.RouteUCService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/routes")
public class RouteController {

  @Autowired
  private RouteUCService routesUCService;

  @GetMapping("/")
  public List<Route> findAllRoutes() {
    return routesUCService.retrieveRoutes();
  }

}
