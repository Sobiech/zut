package pl.zut.zjava.server.connection.protocol.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Validator extends Remote {

    String authorize(String aUserName, String aPassword) throws RemoteException;

}
