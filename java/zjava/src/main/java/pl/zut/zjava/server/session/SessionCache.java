package pl.zut.zjava.server.session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class SessionCache {

    private final static Logger logger = LoggerFactory.getLogger(SessionCache.class);

    private static final SessionCache instance = new SessionCache();

    private final Map<String, SessionDto> sessionMap;


    private SessionCache() {

        sessionMap = new HashMap<>();
        logger.debug("initialized session cache");
    }


    public static SessionCache get() {
        return instance;
    }



    public SessionDto createSession() {

        synchronized (sessionMap) {

            SessionDto sessionDto = new SessionDto();
            sessionMap.put(sessionDto.getSid(), sessionDto);
            logger.debug("createSession(): creataed new session:{}", sessionDto);

            return sessionDto;
        }
    }



    public SessionDto getSession(String sid){

        synchronized (sessionMap) {
            logger.debug("getSession(): by sid:{}", sid);
            Optional<SessionDto> maybeSession = Optional.of(sessionMap.get(sid));

            if (maybeSession.isPresent() && maybeSession.get().isValid()) {
                return maybeSession.get();
            }

            sessionMap.remove(sid);

            return null;
        }
    }



    public List<String> getInvalidSessions() {

        synchronized (sessionMap) {

            logger.debug("getInvalidSessions(): invoked");

            return sessionMap.entrySet()
                    .stream().filter((set) -> !set.getValue().isValid())
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());
        }
    }


    public void evict(String sid){

        synchronized (sessionMap) {
            logger.debug("evict(): by sid:{}", sid);
            sessionMap.remove(sid);
        }
    }


    public void evictAll(){

        synchronized (sessionMap) {
            sessionMap.clear();
        }
    }
}
