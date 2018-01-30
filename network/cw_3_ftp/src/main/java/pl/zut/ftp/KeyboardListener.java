package pl.zut.ftp;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import pl.zut.ftp.protocol.FtpProtocol;

public class KeyboardListener extends JFrame implements KeyListener, ActionListener {
	
	private static final long serialVersionUID = -5112649842873594800L;
	
	private JTextArea displayArea;
	private JTextField typingArea;
	
	private FtpProtocol ftpClient;
	
	public KeyboardListener() throws IOException, InterruptedException {
		super("Client ftp");
		this.initialize();
	}
	
	private void initialize() throws IOException, InterruptedException {
		
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addComponentsToPane();
		this.pack();
		this.setVisible(true);
		
		ftpClient = new FtpProtocol(this.displayArea);
		ftpClient.initializeConnection();
		ftpClient.login(ClientConfig.getFtpLogin(), ClientConfig.getFtpPassword());
	}
	
	private void addComponentsToPane() {
        
		JButton button = new JButton("Clear");
		button.addActionListener(this);
        
		typingArea = new JTextField(20);
		typingArea.addKeyListener(this);
        
		displayArea = new JTextArea();
		displayArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(displayArea);
		scrollPane.setPreferredSize(new Dimension(375, 125));
        
		displayArea.append(String.format("%s\n", "Wcisnij ESC aby wyjsc!"));
		getContentPane().add(typingArea, BorderLayout.PAGE_START);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		getContentPane().add(button, BorderLayout.PAGE_END);
	}
    
	public void actionPerformed(ActionEvent e) {
		displayArea.setText("");
		typingArea.setText("");
		typingArea.requestFocusInWindow();
	}
	
	public void keyReleased(KeyEvent e) {

		typingArea.setText("");

		int keyCode = e.getKeyCode();
		final Navigator navi = Navigator.GetByKeyCode(keyCode);
       
		if ( navi != null ) {
       		displayArea.setCaretPosition(displayArea.getDocument().getLength());
			new Thread(new Runnable() {
				public void run() {
					try {
						ftpClient.sendMessage(navi.getCmd());
					} catch (Exception e) {
						displayArea.append(String.format("error: %s", e.getMessage()));
						e.printStackTrace();
					}
				}
			}).start();
			
			if ( navi.equals(Navigator.ESC)) {
				getDisplayArea().append(String.format("%s\n", "Program wylaczy sie za 3sec"));
				new Thread(new Runnable() {
					public void run() {
						try {
							Thread.sleep(1500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						} finally {
							System.exit(0);
						}
					}
				}).start();
			}
		}
		
	}
	
	public void keyTyped(KeyEvent e) { }
	public void keyPressed(KeyEvent e) { }
	
	public JTextArea getDisplayArea() { 
		return this.displayArea;
	}
	
}
