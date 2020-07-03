package br.com.meli.mlgw.http.controller;

public class RouteNotFoundException extends RuntimeException {

  public RouteNotFoundException(String message) {
    super(message);
  }
}
