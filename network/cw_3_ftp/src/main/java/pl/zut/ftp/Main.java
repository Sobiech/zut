package pl.zut.ftp;
import javax.swing.UIManager;

public class Main {
	
	public static void main(String[] args) throws Exception {
		
		try {
			
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
			UIManager.put("swing.boldMetal", Boolean.FALSE);
			
			javax.swing.SwingUtilities.invokeLater( 
				new Runnable() {
					public void run() {
						try {
							
							KeyboardListener listener = new KeyboardListener();
								
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			);
		} catch(Exception e) {
			
    	   e.printStackTrace();
		}
		
	
		
	}
	
	
}
