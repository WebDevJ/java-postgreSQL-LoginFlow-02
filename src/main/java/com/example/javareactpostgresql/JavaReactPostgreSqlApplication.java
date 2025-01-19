package com.example.javareactpostgresql;


import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class JavaReactPostgreSqlApplication {
    public static void main(String[] args) {

        //System.out.println("Hello, World!");
        // Load .env file
        Dotenv dotenv = Dotenv.load();

         //Set environment variables
        System.setProperty("DB_URL", dotenv.get("DB_URL"));
        System.setProperty("DB_USERNAME", dotenv.get("DB_USERNAME"));
        System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));

        SpringApplication.run(JavaReactPostgreSqlApplication.class, args);
    }
}

@Component
class ApplicationStartupListener implements ApplicationListener<WebServerInitializedEvent> {
    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {
        int port = event.getWebServer().getPort();
        System.out.println("Application is running at: http://localhost:" + port);
    }
}
