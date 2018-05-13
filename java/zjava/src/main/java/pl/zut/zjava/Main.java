package pl.zut.zjava;

import com.google.common.collect.ImmutableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.zut.zjava.entity.PersistenceUnitFactory;
import pl.zut.zjava.jline.Shell;
import pl.zut.zjava.server.connection.protocol.rmi.LoginServer;
import pl.zut.zjava.server.connection.protocol.soap.AbstractWebService;
import pl.zut.zjava.server.connection.protocol.soap.impl.WorkerEndpointServiceImpl;
import pl.zut.zjava.server.connection.protocol.tcp.TcpConnectionServer;
import pl.zut.zjava.server.session.SessionRemoveScheduler;

import javax.xml.ws.Endpoint;
import java.rmi.RemoteException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class Main {


    private static final Logger logger = LoggerFactory.getLogger(Main.class);


    public static final int TCP_SERVER_PORT = 1024;
    public static final int RMI_SERVER_PORT = 1099;


    public static final String PARAM_TOKEN = "token";
    public static final String PARAM_FRAME = "frame";


    public static void main(String[] args) {

        try {

            initializeSchedulers();
            initializePersistenceUnit();

            initializeServerThreadPool();
            initializeRmi();
            initializeWS();

            new Shell().initialize();
        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    private static void initializeRmi() throws RemoteException {

        System.setProperty("sun.rmi.registry.registryFilter", "java.**;pl.zut.zjava.**");
        System.setProperty("java.security.policy", "client.policy");

        new LoginServer().initialize();
    }



    private static void initializePersistenceUnit() {

        logger.debug("Initialized persistence unit: ZJAVA_UNIT");
        PersistenceUnitFactory.ZJAVA_UNIT.createEntityManager();
    }



    private static void initializeServerThreadPool() {

        final ExecutorService ServerThreadPool = Executors.newFixedThreadPool(100);
        logger.debug("Initialized server thread pool of size: 100");
        final ExecutorService ClientServerThreadPool = Executors.newFixedThreadPool(10);
        logger.debug("Initialized client thread pool of size: 10");

        ServerThreadPool.submit(new TcpConnectionServer(TCP_SERVER_PORT, ClientServerThreadPool));
    }


    private static void initializeSchedulers() {

        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        new SessionRemoveScheduler(scheduler);

    }


    private static void initializeWS() {

        ImmutableList<AbstractWebService> serviceList =
                ImmutableList.of( new WorkerEndpointServiceImpl());

        serviceList.forEach(service -> Endpoint.publish(service.getWebServiceURL(), service));
    }

}
