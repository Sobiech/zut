package pl.zut.zjava.server.connection.protocol.tcp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

public class TcpConnectionServer extends Thread {

    private static final Logger logger = LoggerFactory.getLogger(TcpConnectionServer.class);

    private ServerSocket server;

    private Boolean stopServer = false;

    private final ExecutorService executorService;


    public TcpConnectionServer(final Integer port, final ExecutorService executorService) {

        this.executorService = executorService;

        try {

            server = new ServerSocket(port);
            logger.info("Server started at {} on port {}" , InetAddress.getLocalHost(), server.getLocalPort());
        } catch (IOException e) {

            logger.error("An error occur while initialize server", e);
        }
    }


    public void run() {

        while(!stopServer) {

            try {
                final Socket clientSocket = server.accept();
                executorService.submit(new TcpConnectionClient(clientSocket));
            } catch (IOException e) {
                logger.error("An error occur while accepting incoming connection", e);
            }
        }

        logger.info("server stopped");
    }


    public void stopServer(Boolean stopServer) {
        this.stopServer = stopServer;
    }
}
