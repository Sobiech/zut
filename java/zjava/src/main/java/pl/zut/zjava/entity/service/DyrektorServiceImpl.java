package pl.zut.zjava.entity.service;

import pl.zut.zjava.entity.Dyrektor;

import java.sql.SQLException;
import java.util.List;

public class DyrektorServiceImpl 
	extends AbstractDAOService<Dyrektor>{

	public List<Dyrektor> getList() throws SQLException {
		return this.getResultList("SELECT * FROM javaz.dyrektor ", Dyrektor.class, 100);
	}

}
