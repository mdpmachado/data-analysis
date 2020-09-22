package data.analysis.cloud.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class DataAnalysisEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataAnalysisEurekaServerApplication.class, args);
	}
}
