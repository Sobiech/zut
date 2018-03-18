package pl.zut.zjava;

import pl.zut.zjava.entity.Dyrektor;
import pl.zut.zjava.entity.Handlowiec;
import pl.zut.zjava.entity.Pracownik;
import pl.zut.zjava.entity.service.DyrektorServiceImpl;
import pl.zut.zjava.entity.service.HandlowiecServiceImpl;
import pl.zut.zjava.entity.service.PracownikServiceImpl;
import pl.zut.zjava.reflections.EntityHelper;

public class Main {
	
	public static void main(String[] args) {

		PracownikServiceImpl service 	= new PracownikServiceImpl();
		HandlowiecServiceImpl service2 	= new HandlowiecServiceImpl();
		DyrektorServiceImpl service3 	= new DyrektorServiceImpl();
		
		Pracownik prac 	  = service.findById("11122233344", Pracownik.class);
		Handlowiec hand   = service2.findById("11122233354", Handlowiec.class);
		Dyrektor dyrektor = service3.findById("15122233365", Dyrektor.class);
		
		
		try {
			
			StringBuilder simpleOut = new StringBuilder();
			simpleOut.append(
				String.format(
					"\n - Pracownik -> \n"
						+ "DELETE: %s \n"
						+ "UPDATE: %s \n"
						+ "INSERT: %s \n",
					EntityHelper.BuildDeleteQuery(prac), 
					EntityHelper.BuildUpdateQuery(prac),
					EntityHelper.BuildInsertQuery(prac)
				)
			);
			
			simpleOut.append(
				String.format(
					"\n- Handlowiec -> \n"
						+ "DELETE: %s \n"
						+ "UPDATE: %s \n"
						+ "INSERT: %s \n",
					EntityHelper.BuildDeleteQuery(hand), 
					EntityHelper.BuildUpdateQuery(hand),
					EntityHelper.BuildInsertQuery(hand)
				)
			);
			
			simpleOut.append(
				String.format(
					"\n- Dyrektor -> \n"
						+ "DELETE: %s \n"
						+ "UPDATE: %s \n"
						+ "INSERT: %s \n",
					EntityHelper.BuildDeleteQuery(dyrektor), 
					EntityHelper.BuildUpdateQuery(dyrektor),
					EntityHelper.BuildInsertQuery(dyrektor)
				)
			);
			
			System.out.println(simpleOut.toString());
			
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
}
