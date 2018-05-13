package pl.zut.zjava.entity.service;

import pl.zut.zjava.entity.Trader;

import java.util.List;

public interface TraderService extends AbstractDbService<Trader> {

    List<Trader> getTraderList(Integer limit);
}
