package factory.sensorapp.ui;

import factory.sensorapp.tcp.TcpClient;
import factory.serverapp.model.Message;
import factory.serverapp.model.Sensor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;

public class Console {
    private ExecutorService executorService;
    private TcpClient tcpClient;

    public Console(TcpClient tcpClient, ExecutorService executorService) {
        this.executorService = executorService;
        this.tcpClient = tcpClient;
    }

    public void start() {
        System.out.println("Enter sensor data: ");

        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Enter id:");
            int id = Integer.parseInt(bufferRead.readLine());
            System.out.println("Enter name:");
            String name = bufferRead.readLine();
            System.out.println("Enter bounds: ");
            double lowerBound = Double.parseDouble(bufferRead.readLine());
            double upperBound = Double.parseDouble(bufferRead.readLine());

            while (true) {
                Double generatedMeasurement = lowerBound + new Random().nextDouble() * (upperBound - lowerBound);
                Sensor sensor = new Sensor(id, name, generatedMeasurement);

                Message request = new Message("header", sensor.encode().toString());
                tcpClient.sendAndReceive(request);
                Thread.sleep(3000);
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
