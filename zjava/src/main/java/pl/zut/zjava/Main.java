package pl.zut.zjava;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.IntStream;

import pl.zut.zjava.entity.Dyrektor;
import pl.zut.zjava.entity.Handlowiec;
import pl.zut.zjava.entity.Pracownik;
import pl.zut.zjava.entity.service.DyrektorServiceImpl;
import pl.zut.zjava.entity.service.HandlowiecServiceImpl;
import pl.zut.zjava.entity.service.PracownikServiceImpl;

public class Main {
	
	public static void main(String[] args) {
		
		try {
			
			PracownikServiceImpl service 	= new PracownikServiceImpl();
			HandlowiecServiceImpl service2 	= new HandlowiecServiceImpl();
			DyrektorServiceImpl service3 	= new DyrektorServiceImpl();
			
			List<Pracownik> pracownicy = service.getResultList("SELECT * FROM javaz.pracownik", Pracownik.class, 20);
			List<Handlowiec> handlowcy = service2.getResultList("SELECT * FROM javaz.handlowiec", Handlowiec.class, 20);
			List<Dyrektor> dyrektorzy  = service3.getResultList("SELECT * FROM javaz.dyrektor", Dyrektor.class, 20);
			
			pracownicy.forEach(System.out::println);
			handlowcy.forEach (System.out::println);
			dyrektorzy.forEach(System.out::println);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
	
	public static void ExecuteQuery(String query) throws SQLException {
		
		Connection conn = RdbmsConnectionManager.GetConnection();
		ResultSet res = conn.prepareStatement(query).executeQuery();
		
		while ( res.next() ) {
			
			Integer cn = res.getMetaData().getColumnCount();
			StringBuilder sb = new StringBuilder();
			
			IntStream
				.range(1, cn)
				.forEach( i -> {
					try {
						sb.append(res.getObject(i)).append(", ");
					} catch (SQLException e) {
						e.printStackTrace();
					}
			});
			
			System.out.println(sb.toString());
		}
		
		conn.close();
	}
	
}
