package factory.sensorapp.tcp;

import factory.serverapp.model.Message;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

public class TcpClient {
    private ExecutorService executorService;

    public TcpClient(ExecutorService executorService) {
        this.executorService = executorService;
    }

    public Message sendAndReceive(Message request) {
        try (var socket = new Socket("localhost", 1234);
             var is = socket.getInputStream();
             var os = socket.getOutputStream()) {

            System.out.println("sending request: " + request);
            request.writeTo(os);
            System.out.println("request sent");

            Message response = new Message();
            response.readFrom(is);
            System.out.println("received response: " + response);

            return response;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}