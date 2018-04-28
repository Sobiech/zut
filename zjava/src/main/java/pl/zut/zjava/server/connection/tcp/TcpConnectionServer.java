package pl.zut.zjava.server.connection.tcp;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

public class TcpConnectionServer extends Thread {


    private ServerSocket server;

    private Boolean stopServer = false;

    private final ExecutorService executorService;


    public TcpConnectionServer(final Integer port, final ExecutorService executorService) {

        this.executorService = executorService;

        try {
            server = new ServerSocket(port);
            System.out.println(
                "Server started at " + InetAddress.getLocalHost() +
                " on port " + server.getLocalPort()
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void run() {

        while(!stopServer) {
            try {
                final Socket clientSocket = server.accept();
                executorService.submit(new TcpConnectionClient(clientSocket));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Server stopped");
    }


    public void stopServer(Boolean stopServer) {
        this.stopServer = stopServer;
    }
}
