package br.com.meli.mlgw.http.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.constraints.NotBlank;

@Tag(name = "route", description = "Dados de uma Rota")
public class RouteRequest {

  @NotBlank
  @Schema(description = "Caminho de destinho", required = true, example = "/lista")
  @JsonProperty("path_destination")
  private String pathDestination;

  @Schema(description = "Ip de origem", required = true, example = "192.168.10.20")
  @JsonProperty("origin_ip")
  @NotBlank
  private String originIp;

  @Schema(description = "Quantidade máxima de requisições permitidas por segundo", required = true, example = "10")
  @JsonProperty("max_request_per_second")
  @NotBlank
  private Integer maxRequestPerSecond;

  public String getPathDestination() {
    return pathDestination;
  }

  public void setPathDestination(String pathDestination) {
    this.pathDestination = pathDestination;
  }

  public String getOriginIp() {
    return originIp;
  }

  public void setOriginIp(String originIp) {
    this.originIp = originIp;
  }

  public Integer getMaxRequestPerSecond() {
    return maxRequestPerSecond;
  }

  public void setMaxRequestPerSecond(Integer maxRequestPerSecond) {
    this.maxRequestPerSecond = maxRequestPerSecond;
  }
}
