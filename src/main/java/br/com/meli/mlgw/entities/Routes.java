package br.com.meli.mlgw.entities;

public class Routes {

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

  public Routes() { }

  private Routes(Integer id, String pathDestination, String originIp, Integer maxRequestPerSecond) {
    this.id = id;
    this.pathDestination = pathDestination;
    this.originIp = originIp;
    this.maxRequestPerSecond = maxRequestPerSecond;
  }

  public static Routes valueOf(Integer id, String pathDestination, String originIp, Integer maxRequestPerSecond){
    return new Routes(id, pathDestination, originIp, maxRequestPerSecond);
  }
}
