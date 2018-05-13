package pl.zut.zjava.entity.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.zut.zjava.entity.Trader;
import pl.zut.zjava.entity.Worker;
import pl.zut.zjava.entity.service.TraderService;

import java.util.List;

public class TraderServiceImpl
	extends AbstractDbServiceImpl<Trader> implements TraderService {

    private static final Logger logger = LoggerFactory.getLogger(TraderServiceImpl.class);

    public TraderServiceImpl() {
        super(Trader.class);
    }

    @Override
    public List<Trader> getTraderList(Integer limit) {

        long start = System.currentTimeMillis();
        try {

            StringBuilder hql = new StringBuilder("FROM Trader t");
            return getEntityManager().createQuery(hql.toString(), Trader.class).setMaxResults(limit).getResultList();

        } finally {

            logger.debug("getTraderList(): done in :{}[ms]", (System.currentTimeMillis() - start));
        }
    }


    @Override
    protected Logger getLogger() {
        return logger;
    }
}
