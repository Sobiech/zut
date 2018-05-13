package pl.zut.pswa.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.zut.pswa.dto.UserSessionDto;
import pl.zut.pswa.entity.User;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class SessionCache {

    private static final Logger logger = LoggerFactory.getLogger(SessionCache.class);

    private final Map<String, UserSessionDto> sessions;

    private static SessionCache _instance;


    public static SessionCache getInstance() {
        if ( Objects.isNull(_instance) ) {
            _instance = new SessionCache();
        }
        return _instance;
    }


    private SessionCache() {
        sessions = new HashMap<>();
        logger.info("onConstruct(): initialized session cache");
    }


    public UserSessionDto createSession(User user) {
        synchronized (sessions) {
            logger.info("createSession(): by userId:{}", user.getId());
            UserSessionDto sessionDto = UserSessionDto.Build(user);
            sessions.put(sessionDto.getSessionId(), sessionDto);
            logger.info("createSession(): createdSession with id:{}", sessionDto.getSessionId());
            return sessionDto;
        }
    }


    public void removeSession(String sessionId) {
        synchronized (sessions) {
            logger.info("removeSession(): by sessionId:{}", sessionId);
            sessions.remove(sessionId);
        }
    }


    public UserSessionDto getSession(String sessionId) {
        synchronized (sessions) {
            logger.info("getSession(): by sessionId:{}", sessionId);
            return sessions.get(sessionId);
        }
    }


    public Boolean isValid(String sessionId) {

        synchronized (sessions) {
            logger.info("isValid(): by sessionId:{}", sessionId);
            UserSessionDto
                sessionDto = sessions.get(sessionId);
                return !Objects.isNull(sessionDto);
        }
    }
}
