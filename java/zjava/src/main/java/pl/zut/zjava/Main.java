package pl.zut.zjava;

import javassist.bytecode.analysis.Executor;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.directory.ldap.client.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.zut.zjava.commons.enums.PersistenceUnitFactory;
import pl.zut.zjava.entity.AbstractWorker;
import pl.zut.zjava.entity.Director;
import pl.zut.zjava.entity.service.AbstractWorkerService;
import pl.zut.zjava.entity.service.impl.AbstractWorkerServiceImpl;
import pl.zut.zjava.jaxb.SchemaResolver;
import pl.zut.zjava.jaxb.WorkerList;
import pl.zut.zjava.jaxb.XmlUtils;
import pl.zut.zjava.jline.Shell;
import pl.zut.zjava.server.connection.rmi.LoginServer;
import pl.zut.zjava.server.connection.rmi.Validator;
import pl.zut.zjava.server.connection.tcp.TcpConnectionServer;
import pl.zut.zjava.server.session.SessionCache;
import pl.zut.zjava.server.session.SessionRemoveScheduler;

import javax.jms.Session;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.SchemaOutputResolver;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import static java.util.concurrent.TimeUnit.SECONDS;

public class Main {


    private static final Logger logger = LoggerFactory.getLogger(Main.class);


    public static final int TCP_SERVER_PORT = 1024;
    public static final int RMI_SERVER_PORT = 1099;


    public static final String PARAM_TOKEN = "token";
    public static final String PARAM_FRAME = "frame";


    public static void main(String[] args) {

        try {


//            LdapConnectionConfig
//                config = new LdapConnectionConfig();
//                config.setLdapHost( "82.145.72.13" );
//                config.setLdapPort( 389 );
//                config.setTimeout(10);

//            LdapConnection
//                connection = new LdapNetworkConnection( config);

            initializeRmi();
            initializeSchedulers();
            initializePersistenceUnit();
            initializeThreadPools();
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



    private static void initializeThreadPools() {

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

}
