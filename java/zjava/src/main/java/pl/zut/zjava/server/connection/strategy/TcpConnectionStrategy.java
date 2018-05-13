package pl.zut.zjava.server.connection.strategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.zut.zjava.commons.enums.FrameType;
import pl.zut.zjava.entity.Worker;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import static pl.zut.zjava.Main.PARAM_FRAME;
import static pl.zut.zjava.Main.PARAM_TOKEN;

public class TcpConnectionStrategy
        implements ConnectionStrategy {

    private static final Logger logger = LoggerFactory.getLogger(TcpConnectionStrategy.class);


    @SuppressWarnings("unchecked")
    @Override
    public List<Worker> getWorkerList(String host, Integer port, String sid)
            throws Exception {

        long start = System.currentTimeMillis();
        logger.debug("getWorkerList(): {}:{} by sid:{}",host,port, sid);

        try {
            Socket client = new Socket();
            client.connect(new InetSocketAddress(host, port), 10);

            if (!client.isConnected()) {
                throw new ConnectException();
            }

            DataOutputStream
                dos = new DataOutputStream(client.getOutputStream());
                dos.writeUTF(buildFrameToSend(sid, FrameType.F_GET_ALL));
                dos.flush();

            DataInputStream dis = new DataInputStream(client.getInputStream());
            ObjectInputStream ois = new ObjectInputStream(dis);

            List<Worker> workerList = new ArrayList<>();
            try {
                workerList = (List<Worker>) ois.readObject();
            } catch (Exception e) {
                logger.error("An error occur while trying to deserialize object", e);
            }

            dis.close();
            ois.close();

            return workerList;
        } finally {

            logger.debug("getWorkerList(): done in {}[ms]", ( System.currentTimeMillis() - start ));
        }

    }



    private static String buildFrameToSend(String sid, FrameType frameType) {

        return PARAM_TOKEN.concat("=").concat(sid)
            .concat("&")
            .concat(PARAM_FRAME).concat("=").concat(frameType.getFrameName());
    }

}
