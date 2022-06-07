package factory.serverapp.config;

import factory.serverapp.repository.SensorRepository;
import factory.serverapp.service.SensorService;
import factory.serverapp.tcp.TcpServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Configuration
@ComponentScan({"factory.serverapp.repository", "factory.serverapp.service",
        "factory.serverapp.tcp", "factory.serverapp.model"})
public class ServerConfig {
    @Bean
    ExecutorService executorService() {
        return Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors()
        );
    }

    @Bean
    TcpServer serverApp() {
        SensorRepository sensorRepository = sensorRepository();
        ExecutorService executorService = executorService();

        return new TcpServer(
                sensorService(sensorRepository, executorService),
                executorService);
    }

    @Bean
    SensorRepository sensorRepository() {
        return new SensorRepository();
    }

    @Bean
    SensorService sensorService(SensorRepository sensorRepository,
                                ExecutorService executorService) {
        return new SensorService(sensorRepository, executorService);
    }
}