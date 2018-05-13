package pl.zut.zjava.entity.service;

import pl.zut.zjava.entity.Director;

import java.util.List;

public interface DirectorService extends AbstractDbService<Director> {

    List<Director> getDirectorList(Integer limit);

}
