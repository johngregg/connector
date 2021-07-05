package org.johngregg.connector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConnectorApplication {

	public static void main(String[] args) {
//		SpringApplication application = new SpringApplication(ConnectorApplication.class);
//		application.addListeners(new MyListener());
//		application.run(args);
		SpringApplication.run(ConnectorApplication.class, args);
	}

}
