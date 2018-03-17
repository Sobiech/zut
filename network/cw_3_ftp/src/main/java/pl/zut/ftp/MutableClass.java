package pl.zut.ftp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class MutableClass {
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		StringBuilder sb = new StringBuilder();
		IntStream
			.range(1, 10000)
			.forEach(i -> sb.append("A"));  
		
		Integer initialCapacity = 4;
		List<String> stringCollection  = new ArrayList<>(initialCapacity);
		Map<String,String> stringPool  = new HashMap<>(initialCapacity); 
		
	}
	
	
	private final String strA;
	
	private final String strB;
	
	/**
	 * 
	 * @param a
	 * @param b
	 */
	public MutableClass(String a, String b) {
		this.strA = a;
		this.strB = b;
	}

	public String getStrA() {
		return strA;
	}

	public String getStrB() {
		return strB;
	}
	
}
