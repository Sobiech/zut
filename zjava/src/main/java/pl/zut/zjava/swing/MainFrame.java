package pl.zut.zjava.swing;

import pl.zut.zjava.server.connection.tcp.TcpConnectionServer;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainFrame extends JFrame {

    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 600;

    private static final String FRAME_TITLE = "Ewidencja pracownika";

    private static final int TCP_SERVER_PORT = 100;


    public static void main(String[] args) {

        final ExecutorService ServerThreadPool = Executors.newFixedThreadPool(100);
        final ExecutorService ClientServerThreadPool = Executors.newFixedThreadPool(10);

        try {

            UIManager.setLookAndFeel(
                UIManager.getSystemLookAndFeelClassName());

            ServerThreadPool.submit(new TcpConnectionServer(TCP_SERVER_PORT, ClientServerThreadPool));
            ServerThreadPool.submit(() -> new MainFrame().setVisible(true));

        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    private MainFrame() {
        super(FRAME_TITLE);
        this.initialize();
    }


    private void initialize(){

        MainPanel mainPanel = new MainPanel();
        this.setContentPane(mainPanel.getContentPane());

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    }
}
