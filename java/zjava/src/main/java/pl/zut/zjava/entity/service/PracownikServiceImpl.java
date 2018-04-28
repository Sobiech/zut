package pl.zut.zjava.entity.service;

import pl.zut.zjava.entity.Pracownik;

import java.sql.SQLException;
import java.util.List;

public class PracownikServiceImpl 
	extends AbstractDAOService<Pracownik> {

	public List<Pracownik> getList() throws SQLException {
		return this.getResultList("SELECT * FROM javaz.pracownik ", Pracownik.class, 100);
	}

}
