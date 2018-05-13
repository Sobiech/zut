package pl.zut.zjava.server.connection.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;
import java.util.Optional;

public interface Validator extends Remote {

    String authorize(String aUserName, String aPassword) throws RemoteException;

}
