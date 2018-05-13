package pl.zut.zjava.server.connection.protocol.tcp;

import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.zut.zjava.commons.enums.FrameType;
import pl.zut.zjava.server.processor.GetWorkerFrameProcessor;
import pl.zut.zjava.server.session.SessionCache;
import pl.zut.zjava.server.session.SessionDto;

import java.io.*;
import java.net.Socket;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.Map;

import static pl.zut.zjava.Main.PARAM_FRAME;
import static pl.zut.zjava.Main.PARAM_TOKEN;

class TcpConnectionClient extends Thread {

    private static final Logger logger = LoggerFactory.getLogger(TcpConnectionClient.class);

    private final Socket clientSocket;

    private final String logMessage = "[ Thread id: %s ] client:%s %s";

    public static final String CLIENT_WELCOME =
            "Dostepne komendy:\n" +
                    " - F_GET_ALL: Lista pracownikow \n" +
                    " - F_HELP: Drukuje powyzsza liste\n\n";


    TcpConnectionClient(final Socket clientSocket) {
        this.clientSocket = clientSocket;
        logger.debug(String.format(logMessage, this.getId(), clientSocket.toString(), "/ new client connected"));
    }


    @SuppressWarnings("unchecked")
    public void run() {

        try {

            DataInputStream     dis = new DataInputStream(clientSocket.getInputStream());
            ObjectOutputStream  oos = new ObjectOutputStream(clientSocket.getOutputStream());

            String message = dis.readUTF();
            logger.info(String.format(logMessage, this.getId(), clientSocket.toString(), "/ processing message: " + message ));

            Map<String, String> paramMap = splitMessage(message);

            String token = paramMap.get(PARAM_TOKEN);
            if (Strings.isNullOrEmpty(token)) {
                clientSocket.close();
                logger.warn("Unauthorized access");
            }

            String sid = new String(Base64.getDecoder().decode(token.getBytes()));
            SessionDto session = SessionCache.get().getSession(sid);
            if (session == null || !session.isValid() ){
                clientSocket.close();
                logger.warn("Unauthorized access");
            }

            try {

                FrameType frameType = FrameType.GetFrameByName(paramMap.get(PARAM_FRAME));
                if ( frameType.equals(FrameType.F_GET_ALL)) {
                    oos.writeObject(new GetWorkerFrameProcessor().getWorkerList());
                } else if ( frameType.equals(FrameType.F_HELP) ) {
                    oos.writeUTF(CLIENT_WELCOME);
                }
            } catch ( IllegalArgumentException e ){

                oos.writeUTF(e.getMessage());
            }

            oos.flush();
            oos.close();
            dis.close();

            SessionCache.get().evict(sid);
        } catch (IOException e) {

            logger.error("An error occur while processing client request", e);
        }
    }


    private static Map<String, String> splitMessage(String message) {

        Map<String, String> queryPairs = new LinkedHashMap<>();
        String[] pairs = message.split("&");

        for (String pair : pairs) {
            int idx = pair.indexOf("=");
            queryPairs.put( pair.substring(0, idx), pair.substring(idx + 1) );
        }

        return queryPairs;
    }

}
