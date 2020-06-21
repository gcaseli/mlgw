package br.com.meli.mlgw;

import br.com.meli.mlgw.entities.Routes;
import br.com.meli.mlgw.entities.UriMeliConfiguration;
import br.com.meli.mlgw.externals.database.RouteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder.Builder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(UriMeliConfiguration.class)
public class MlgwApplication {

	@Autowired
	private RouteRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(MlgwApplication.class, args);
	}
	
	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder, UriMeliConfiguration uriConfiguration) {

		List<Routes> routesDatabase = repository.retrieveRoutes();

		String httpUri = uriConfiguration.getHttpbin();

		Builder routes = builder.routes();

		for (Routes r : routesDatabase) {
			routes
					.route(p -> p
							.path(r.getPathDestination())
							.uri(httpUri));
		}

		return routes.build();
	}

}
