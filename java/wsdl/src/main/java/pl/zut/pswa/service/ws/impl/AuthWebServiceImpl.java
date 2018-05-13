package pl.zut.pswa.service.ws.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.zut.pswa.dto.AuthData;
import pl.zut.pswa.dto.UserSessionDto;
import pl.zut.pswa.enums.Role;
import pl.zut.pswa.service.SessionCache;
import pl.zut.pswa.service.db.AuthDbService;
import pl.zut.pswa.service.db.impl.AuthDbServiceImpl;
import pl.zut.pswa.service.ws.AuthWebService;

import pl.zut.pswa.entity.User;

import javax.jws.WebService;
import java.util.Objects;

@WebService(endpointInterface = "pl.zut.pswa.service.ws.AuthWebService")
public class AuthWebServiceImpl
        extends AbstractWebServiceImpl implements AuthWebService {

    private static final Logger logger = LoggerFactory.getLogger(AuthWebServiceImpl.class);

    private static final String PATH = "/auth";


    private SessionCache sessionCache;
    private AuthDbService authDbService;


    public AuthWebServiceImpl() {
        sessionCache = SessionCache.getInstance();
        authDbService = new AuthDbServiceImpl();
    }


    @Override
    public String login(AuthData authData)
            throws Exception {

        long start = System.currentTimeMillis();
        try {
            logger.info("login(): by credentials {}", authData );

            if ( Objects.isNull(authData.getEmail())
                    || Objects.isNull(authData.getPassword())) {
                throw new Exception("Invalid credentials data!");
            }

            User user = authDbService.findByEmailPasswordAndRole(authData.getEmail(), authData.getPassword(), Role.ADMIN);
            if ( Objects.isNull(user) ) {
                throw new Exception("Unauthorized!");
            }
            return sessionCache.createSession(user).getSessionId();
        } finally {
            logger.debug("login(): done in {}[ms]", ( System.currentTimeMillis() - start ) );
        }
    }

    @Override
    public Boolean logout(String sid)
            throws Exception {

        long start = System.currentTimeMillis();
        try {
            logger.info("logout(): by sessionId: {}", sid);
            validateParameters(sid);
            if ( !sessionCache.isValid(sid) ) {
                throw new Exception("Unauthorized !");
            }
            sessionCache.removeSession(sid);
            return true;
        } finally {
            logger.debug("logout(): done in {}[ms]", ( System.currentTimeMillis() - start ) );
        }
    }


    @Override
    protected String getWebServicePath() {
        return PATH;
    }

    @Override
    protected Logger getLogger() {
        return logger;
    }
}
