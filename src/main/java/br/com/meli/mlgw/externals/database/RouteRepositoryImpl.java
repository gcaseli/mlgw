package br.com.meli.mlgw.externals.database;

import br.com.meli.mlgw.entities.RouteML;
import br.com.meli.mlgw.http.controller.RouteNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

/**
 * Classe responsável por realizar a integração com o banco de dados onde as rotas estão salvas.
 */
@Repository
public class RouteRepositoryImpl implements RouteRepository {

  private static final String PATH_DESTINATION = "path_destination";
  private static final String ORIGIN_IP = "origin_ip";
  private static final String MAX_REQUEST_PER_SECOND = "max_request_per_second";
  private static final String SELECT_FROM_ROUTE = "select * from route";
  private static final String INSERT = "INSERT INTO route (id, path_destination, origin_ip, max_request_per_second) VALUES (";
  public static final String WHERE_CLAUSE = " WHERE id = :id;";
  private static final String DELETE = "DELETE FROM route" + WHERE_CLAUSE;

  @Autowired
  private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  public List<RouteML> retrieveRoutes() {

    return namedParameterJdbcTemplate.query(SELECT_FROM_ROUTE, (rs, rowNumber) -> RouteML.valueOf(
        rs.getInt("id"),
        rs.getString(PATH_DESTINATION),
        rs.getString(ORIGIN_IP),
        rs.getInt(MAX_REQUEST_PER_SECOND)));

  }

  @Override
  public RouteML createNewRoute(RouteML routeML) {

    MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue(PATH_DESTINATION, routeML.getPathDestination());
    params.addValue(ORIGIN_IP, routeML.getOriginIp());
    params.addValue(MAX_REQUEST_PER_SECOND, routeML.getMaxRequestPerSecond());

    String query = INSERT
        + "(select nextval('sq_route_id')), "
        + ":" + PATH_DESTINATION + ","
        + ":" + ORIGIN_IP + ","
        + ":" + MAX_REQUEST_PER_SECOND + ");";

    namedParameterJdbcTemplate.update(query, params);

    return null;
  }

  @Override
  public int delete(String routeId) {

    MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("id", Integer.valueOf(routeId));

    return namedParameterJdbcTemplate.update(DELETE, params);
  }

  @Override
  public Mono<Object> retrieveRoute(Mono<String> routeId) {
    return routeId.map(id -> {
      MapSqlParameterSource params = new MapSqlParameterSource();
      params.addValue("id", Integer.valueOf(id));

      try {
        return namedParameterJdbcTemplate
            .queryForObject(SELECT_FROM_ROUTE + WHERE_CLAUSE, params,
                (rs, rowNumber) -> RouteML.valueOf(
                    rs.getInt("id"),
                    rs.getString(PATH_DESTINATION),
                    rs.getString(ORIGIN_IP),
                    rs.getInt(MAX_REQUEST_PER_SECOND)));
      } catch (CannotGetJdbcConnectionException eg){
        return Mono.defer(() -> Mono.error(
            new RuntimeException("route not found: " + id)));
      } catch (DataAccessException e) {
        return Mono.defer(() -> Mono.error(
            new RouteNotFoundException("route not found: " + id)));
      }

    });

  }

}
