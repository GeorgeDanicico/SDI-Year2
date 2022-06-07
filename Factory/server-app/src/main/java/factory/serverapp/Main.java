package factory.serverapp;

import factory.serverapp.tcp.TcpServer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext("factory.serverapp.config");

        ExecutorService executorService = Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors()
        );

        TcpServer serverApp = context.getBean(TcpServer.class);
        serverApp.startServer();
    }
}