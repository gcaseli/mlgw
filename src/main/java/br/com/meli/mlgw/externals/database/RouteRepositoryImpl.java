package br.com.meli.mlgw.externals.database;

import br.com.meli.mlgw.entities.Route;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RouteRepositoryImpl implements RouteRepository{

  @Autowired
  private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  public List<Route> retrieveRoutes() {

    return namedParameterJdbcTemplate.query("select * from route", (rs, rowNumber) -> Route.valueOf(
        rs.getInt("id"),
        rs.getString("path_destination"),
        rs.getString("origin_ip"),
        rs.getInt("max_request_per_second")));

  }
}
