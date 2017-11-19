package pl.zut.tests;

import static org.junit.Assert.assertTrue;

import java.util.Base64;

import org.junit.Test;

import pl.zut.base64.InternalBase64;

public class TestBase64 {

	public static final String DATA_TO_ENCODE = "Jakis Testowy String";
	
	public static final String DATA_TO_DECODE = "Q3p5IHRvIGR6aWFsYSA/";
	
	@Test
	public void test1_encoder() {
		
		System.out.println("\n\n TEST test_encoder \n");
		
		System.out.println("Encoding data: " + DATA_TO_ENCODE);
		
		String encInternal = InternalBase64.encode(DATA_TO_ENCODE);
		System.out.println("Encoded data by internal encoder: " + encInternal);
		
		String encExternal = Base64.getEncoder().encodeToString(DATA_TO_ENCODE.getBytes());
		System.out.println("Encoded data by external encoder: " + encExternal );
		
		assertTrue(encInternal.equals(encExternal));
	}
	
	@Test
	public void test2_decoder() {

		System.out.println("\n\n TEST test_decoder \n");
		
		System.out.println("Decoding data: " + DATA_TO_DECODE);

		String decInternal = InternalBase64.decode(DATA_TO_DECODE);
		System.out.println("Decoded data by internal decoder: " + decInternal);
		
		String decExternal = new String(Base64.getDecoder().decode(DATA_TO_DECODE.getBytes()));
		System.out.println("Decoded data by external decoder: " + decExternal);
		
		assertTrue(decInternal.equals(decExternal));
	}
	
	@Test
	public void test3_enc_dec()  {
		
		System.out.println("\n\n TEST test_dec_enc \n");
		
		
		String encInternal = InternalBase64.encode(DATA_TO_ENCODE);
		System.out.println("Encoded data by internal encoder: " + encInternal);
		
		String decInternal = InternalBase64.decode(encInternal);
		System.out.println("Decoded data by internal decoder: " + decInternal);
	
		
		String encExternal = Base64.getEncoder().encodeToString(DATA_TO_ENCODE.getBytes());
		System.out.println("Encoded data by external encoder: " + encExternal );

		String decExternal = new String(Base64.getDecoder().decode(encExternal.getBytes()));
		System.out.println("Decoded data by external decoder: " + decExternal);
		
		Boolean testCondition = 
				decExternal.equals(decInternal) &&
				encExternal.equals(encInternal) &&
				decExternal.equals(DATA_TO_ENCODE) &&
				decInternal.equals(DATA_TO_ENCODE);
				
		assertTrue(testCondition);
	}
	
	
	
}
