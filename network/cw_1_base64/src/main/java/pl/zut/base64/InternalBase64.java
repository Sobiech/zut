package pl.zut.base64;

public class InternalBase64 {
	
	private final static char[] CHAR_POOL;
	private final static int[]  INT_POOL;
	
	static {
		
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
		CHAR_POOL = new StringBuilder()
				.append(alphabet)
				.append(alphabet.toLowerCase())
				.append("0123456789+/")
				.toString()
				.toCharArray();
		
		INT_POOL = new int[128];
		
		for(int i = 0 ; i < CHAR_POOL.length; i ++ ) {
			
			INT_POOL[ CHAR_POOL [i] ] = i;
		}
	}
	
	public static String decode(String origin) {
		
		int delta = 
			origin.endsWith( "==" ) ? 
				2 : origin.endsWith( "=" ) ? 
					1 : 0;
        
		byte[] buffer = new byte[origin.length() * 3/4 - delta];
        int decodeMask = 0xFF;
        
        int currentIndex = 0;
        
        for (int i=0; i< origin.length(); i+=4) {
            
        	Integer c0 = INT_POOL[origin.charAt( i )];
        	Integer c1 = INT_POOL[origin.charAt( i + 1)];
            
            buffer[currentIndex++]= (byte)(((c0 << 2) | (c1 >> 4)) & decodeMask);
           
            if(currentIndex >= buffer.length) {
                return new String(buffer);
            }
            
            int c2 = INT_POOL[origin.charAt( i + 2)];
            
            buffer[currentIndex++]= (byte) ( ( (c1 << 4) | (c2 >> 2) ) & decodeMask);
            
            if(currentIndex >= buffer.length) {
            	
                return new String(buffer);
            }
            
            int c3 = INT_POOL[origin.charAt( i + 3 )];
            buffer[currentIndex++]= (byte)( ( (c2 << 6) | c3 ) & decodeMask);
        }
        
        return new String(buffer);
	}
 	
	
	public static String encode (String origin) {
		
		byte[] buffer = origin.getBytes();
		int bufferSize = buffer.length;
		char[] encodedPool = new char[ ( (bufferSize + 2) / 3) * 4];
        
		int a = 0;
        int i=0;
        
        while( i < bufferSize ) {
            
        	byte b0 = buffer[i++];
            byte b1 = (i < bufferSize) ? buffer[i++] : 0;
            byte b2 = (i < bufferSize) ? buffer[i++] : 0;

            int encodeMask = 0x3F;
            
            encodedPool[a++] = CHAR_POOL[  (b0 >> 2) & encodeMask];
            encodedPool[a++] = CHAR_POOL[( (b0 << 4) | ((b1 & 0xFF) >> 4)) & encodeMask];
            encodedPool[a++] = CHAR_POOL[( (b1 << 2) | ((b2 & 0xFF) >> 6)) & encodeMask];
            encodedPool[a++] = CHAR_POOL[b2 & encodeMask];
        
        }
        
        switch(bufferSize % 3) {
            case 1: encodedPool[--a]  = '=';
            case 2: encodedPool[--a]  = '=';
        }
        
        return new String(encodedPool);
	}
	
}
