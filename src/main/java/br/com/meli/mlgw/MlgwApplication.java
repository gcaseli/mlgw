package br.com.meli.mlgw;

import br.com.meli.mlgw.usecases.routes.RouteUCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class MlgwApplication {

	@Autowired
	private RouteUCService service;

	public static void main(String[] args) {
		SpringApplication.run(MlgwApplication.class, args);
	}

	/**
	 * Put all start-up code to this method.
	 */
	@EventListener(ApplicationReadyEvent.class)
	public void startup() {
		service.updateGatewayRoutes();
	}

}
