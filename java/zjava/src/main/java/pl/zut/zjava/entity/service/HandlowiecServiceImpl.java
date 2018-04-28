package pl.zut.zjava.entity.service;

import pl.zut.zjava.entity.Handlowiec;

import java.sql.SQLException;
import java.util.List;

public class HandlowiecServiceImpl 
	extends AbstractDAOService<Handlowiec> {

	public List<Handlowiec> getList() throws SQLException {
		return this.getResultList("SELECT * FROM javaz.handlowiec ", Handlowiec.class, 100);
	}

}
