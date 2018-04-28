package pl.zut.zjava.server.connection.tcp;

import pl.zut.zjava.commons.FrameType;
import pl.zut.zjava.server.processor.FrameProcessor;
import pl.zut.zjava.server.processor.TcpFrameProcessor;

import java.io.*;
import java.net.Socket;

class TcpConnectionClient extends Thread {

    private final Socket clientSocket;

    private final String logMessage = "[ Thread id: %s ] client:%s %s";

    TcpConnectionClient(final Socket clientSocket) {
        this.clientSocket = clientSocket;
        System.out.println(String.format(logMessage, this.getId(), clientSocket.toString(), "/ new client connected"));
    }


    @SuppressWarnings("unchecked")
    public void run() {

        try {
            PrintWriter out   = new PrintWriter(clientSocket.getOutputStream(),true);
            out.println(TcpFrameProcessor.CLIENT_WELCOME);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String line;
            while((line = in.readLine()) != null) {
                System.out.println(String.format(logMessage, this.getId(), clientSocket.toString(), "/ processing message: " + line ));
                try {
                    FrameType frameType = FrameType.GetFrameByName(line);
                    FrameProcessor frameProcessor = new TcpFrameProcessor();
                    out.println(frameProcessor.processFrame(frameType));
                } catch ( IllegalArgumentException e ) {
                    out.println(e.getMessage());
                }
            }

            out.println("Zamykam polaczenie");
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
