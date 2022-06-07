package factory.sensorapp;

import factory.sensorapp.tcp.TcpClient;
import factory.sensorapp.ui.Console;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors()
        );

        TcpClient tcpClient = new TcpClient(executorService);

        Console clientConsole = new Console(tcpClient, executorService);
        clientConsole.start();

        executorService.shutdown();
    }
}
