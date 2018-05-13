package pl.zut.zjava.server.session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import static java.util.concurrent.TimeUnit.SECONDS;

public class SessionRemoveScheduler implements Runnable{


    private static final Logger logger = LoggerFactory.getLogger(SessionRemoveScheduler.class);


    public SessionRemoveScheduler(final ScheduledExecutorService executorService) {

        final ScheduledFuture<?> schedulerHandler =
            executorService.scheduleAtFixedRate(this, 60, 60, SECONDS);

        executorService.schedule(() -> { schedulerHandler.cancel(true); }, 60 * 60, SECONDS);
    }



    @Override
    public void run() {

        long start = System.currentTimeMillis();
        logger.debug("SessionRemoveScheduler started");
        try {

            List<String> sidList = SessionCache.get().getInvalidSessions();
            logger.debug("Got {} invalid session ", sidList.size());
            sidList.forEach(sid -> SessionCache.get().evict(sid));
        } finally {

            logger.debug("done work in {}[ms]", (System.currentTimeMillis() - start));
        }

    }
}
