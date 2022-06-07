package factory.serverapp.tcp;

import factory.serverapp.model.Message;
import factory.serverapp.model.Sensor;
import factory.serverapp.repository.SensorRepository;
import factory.serverapp.service.SensorService;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.function.UnaryOperator;

public class TcpServer {
    protected ExecutorService executorService;
    private SensorService sensorService;
    protected int port;
    protected Map<String, UnaryOperator<Message>> methodHandlers;

    public TcpServer(SensorService sensorService, ExecutorService executorService) {
        this.executorService = executorService;
        this.port = 1234;
        this.methodHandlers = new HashMap<>();
        this.sensorService = sensorService;
    }

    public void addHandler(String methodName, UnaryOperator<Message> handler) {
        methodHandlers.put(methodName, handler);
    }

    public void startServer() {
        try (var serverSocket = new ServerSocket(this.port)) {
            System.out.println("server started; waiting for clients...");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("client connected");
                executorService.submit(new ClientHandler(clientSocket, sensorService));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class ClientHandler implements Runnable {
        private Socket socket;
        private SensorService sensorService;

        public ClientHandler(Socket socket, SensorService sensorService) {
            this.sensorService = sensorService;
            this.socket = socket;
        }

        @Override
        public void run() {
            try (var is = socket.getInputStream();
                 var os = socket.getOutputStream()) {

                //read the request (of type Message) from client
                Message request = new Message();
                request.readFrom(is);
                System.out.println("received request: " + request);

                Sensor sensor = Sensor.decode(new JSONObject(request.getBody()));

                System.out.println(this.sensorService.addEntity(sensor).get());

                // compute response (of type Message)
                Message response = new Message("header", "received");
                System.out.println("computed response: " + response);

                //send response (of type Message) to client
                response.writeTo(os);
                System.out.println("response sent to client");

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}