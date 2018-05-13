package pl.zut.zjava.server.connection.protocol.rmi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.zut.zjava.Main;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.Map;

public class LoginServer extends Thread {

    private static final Logger logger = LoggerFactory.getLogger(LoginServer.class);

    public final static String REMOTE_METHOD_NAME = "VALIDATOR";

    public void initialize() throws RemoteException {

        Map<String,String> userData = new HashMap<>();
        userData.put("root","root");

        Registry registry = LocateRegistry.createRegistry(Main.RMI_SERVER_PORT);
        ValidatorImpl stub = new ValidatorImpl(userData);
        registry.rebind(REMOTE_METHOD_NAME, stub);

        logger.debug("RMI registered properly on port:{}", Main.RMI_SERVER_PORT);
    }

}
