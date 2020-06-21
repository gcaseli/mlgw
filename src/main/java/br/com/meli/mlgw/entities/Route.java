package br.com.meli.mlgw.entities;

public class Route {

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

  public Route() { }

  private Route(Integer id, String pathDestination, String originIp, Integer maxRequestPerSecond) {
    this.id = id;
    this.pathDestination = pathDestination;
    this.originIp = originIp;
    this.maxRequestPerSecond = maxRequestPerSecond;
  }

  public static Route valueOf(Integer id, String pathDestination, String originIp, Integer maxRequestPerSecond){
    return new Route(id, pathDestination, originIp, maxRequestPerSecond);
  }
}
