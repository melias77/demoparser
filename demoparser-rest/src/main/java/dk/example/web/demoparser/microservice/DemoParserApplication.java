
package dk.example.web.demoparser.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * @author mnb@kmd.dk
 *
 */

@SpringBootApplication
@EnableAutoConfiguration
public class DemoParserApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(DemoParserApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoParserApplication.class, args);
	}

}
