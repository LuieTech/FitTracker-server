package luitech.java.trainerTracker_server;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class TrainerTrackerServerApplication {

	@Value("${frontend.url}")
	private String frontendUrl;

	@Bean
	public WebMvcConfigurer corsConfigurer(){

		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry){
				registry.addMapping("/api/**")
						.allowedOrigins(frontendUrl)
						.allowedMethods("*")
						.allowCredentials(true);
			}
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(TrainerTrackerServerApplication.class, args);
	}

}
