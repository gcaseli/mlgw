package br.com.meli.mlgw.usecases.routes;

import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

/**
 * Componente responsável por publicar alterações das rotas
 */
@Component
public class GatewayRoutesRefresher implements ApplicationEventPublisherAware {

  private ApplicationEventPublisher publisher;

  @Override
  public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
    publisher = applicationEventPublisher;
  }

  public void refreshRoutes() {
    publisher.publishEvent(new RefreshRoutesEvent(this));
  }

}
