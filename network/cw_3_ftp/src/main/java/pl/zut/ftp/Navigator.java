package pl.zut.ftp;

public enum Navigator {
	
	UP(38,"LIST"),
	DOWN(40,"HELP"),
	LEFT(37,"PWD"),
	RIGHT(39,"SYST"),
	ESC(27, "QUIT");
	
	private Integer keyCode;
	
	private String cmd;
	
	Navigator(Integer keyCode, String cmd) {
		this.keyCode = keyCode;
		this.cmd =cmd;
	}
	
	public static Navigator GetByKeyCode(Integer keycode) {
		
		for ( Navigator type: values() ) {
			if ( type.keyCode.equals(keycode) ) {
				return type;
			}
		}
		
		return null;
	}
	
	public String getCmd() {
		return this.cmd;
	}
}
