package br.com.meli.mlgw.http.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * Classe que trata os possíveis erros na aplicação para retornar ao cliente
 */
@Configuration
@Order(-2)
public class GlobalErrorWebExceptionHandler implements ErrorWebExceptionHandler {

  private final ObjectMapper objectMapper;

  public GlobalErrorWebExceptionHandler(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  @Override
  public Mono<Void> handle(ServerWebExchange serverWebExchange, Throwable throwable) {
    DataBuffer dataBuffer = null;
    DataBufferFactory bufferFactory = serverWebExchange.getResponse().bufferFactory();
    try {
      if (throwable instanceof RouteNotFoundException) {
        serverWebExchange.getResponse().setStatusCode(HttpStatus.NOT_FOUND);
        dataBuffer = bufferFactory.wrap(objectMapper
            .writeValueAsBytes(new HttpError(throwable.getMessage(), HttpStatus.NOT_FOUND)));
        serverWebExchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
      }else if (throwable instanceof WebExchangeBindException) {
        FieldError fieldError = ((WebExchangeBindException) throwable).getFieldError();
        serverWebExchange.getResponse().setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
        serverWebExchange.getResponse().getHeaders().setContentType(MediaType.TEXT_PLAIN);
        assert fieldError != null;
        dataBuffer = bufferFactory.wrap(objectMapper
            .writeValueAsBytes(
                new HttpError(fieldError.getDefaultMessage(), HttpStatus.BAD_REQUEST)));
      }

    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    return serverWebExchange.getResponse().writeWith(Mono.just(dataBuffer));
  }

  public static class HttpError {

    private final HttpStatus errorStatus;

    private final String message;

    HttpError(String message, HttpStatus errorStatus) {
      this.message = message;
      this.errorStatus = errorStatus;
    }

    public HttpStatus getErrorStatus() {
      return errorStatus;
    }

    public String getMessage() {
      return message;
    }
  }

}
