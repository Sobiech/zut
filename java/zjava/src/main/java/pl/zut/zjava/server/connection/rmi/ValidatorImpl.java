package pl.zut.zjava.server.connection.rmi;

import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.zut.zjava.server.session.SessionCache;
import pl.zut.zjava.server.session.SessionDto;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Base64;
import java.util.Map;

public class ValidatorImpl
        extends UnicastRemoteObject implements Validator, Serializable {

    private static final Logger logger = LoggerFactory.getLogger(ValidatorImpl.class);

    private Map<String,String> memberMap;


    public ValidatorImpl(Map<String,String> data)
            throws RemoteException {

        logger.debug("Initialized member map");
        memberMap = data;
    }


    @Override
    public String authorize(String aUserName, String aPassword)
            throws RemoteException {

        logger.debug("authorize(): by userName:{} and password:{}", aUserName, aPassword);
        String pass = memberMap.get(aUserName);
        if ( Strings.isNullOrEmpty(pass) || ( !Strings.isNullOrEmpty(pass) && !pass.equals(aPassword)) ) {
            throw new RemoteException("Podane dane sa niepoprawne");
        }

        SessionDto sessionDto = SessionCache.get().createSession();
        logger.debug("authorize(): created new session with sid:{}", sessionDto.getSid());
        return Base64.getEncoder().encodeToString(sessionDto.getSid().getBytes());
    }


}
