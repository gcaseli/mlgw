package br.com.meli.mlgw.entities;

/**
 * Classe que representa as rotas que o sistema cuida
 */
public class RouteML {

  private Integer id;
  private String pathDestination;
  private String originIp;
  private Integer maxRequestPerSecond;

  public Integer getId() {
    return id;
  }

  public String getPathDestination() {
    return pathDestination;
  }

  public String getOriginIp() {
    return originIp;
  }

  public Integer getMaxRequestPerSecond() {
    return maxRequestPerSecond;
  }

  public RouteML() { }

  private RouteML(Integer id, String pathDestination, String originIp, Integer maxRequestPerSecond) {
    this.id = id;
    this.pathDestination = pathDestination;
    this.originIp = originIp;
    this.maxRequestPerSecond = maxRequestPerSecond;
  }

  public static RouteML valueOf(Integer id, String pathDestination, String originIp, Integer maxRequestPerSecond){
    return new RouteML(id, pathDestination, originIp, maxRequestPerSecond);
  }

  public static RouteML createNewRoute(String pathDestination, String originIp, Integer maxRequestPerSecond){
    return new RouteML(null, pathDestination, originIp, maxRequestPerSecond);
  }
}
