package pl.zut.zjava.commands;

import org.jline.reader.LineReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.zut.zjava.commons.FrameType;
import pl.zut.zjava.entity.AbstractWorker;
import pl.zut.zjava.server.connection.rmi.LoginServer;
import pl.zut.zjava.server.connection.rmi.Validator;

import java.io.*;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import static pl.zut.zjava.Main.PARAM_FRAME;
import static pl.zut.zjava.Main.PARAM_TOKEN;


public class DataNetworkDownloadCommand implements ICommand {

    private static final Logger logger = LoggerFactory.getLogger(DataNetworkDownloadCommand.class);

    private static final String INFO =
        "\t\n5.1 Pobierz dane z sieci\n" +
        "-----------------------------------------------\n";

    @SuppressWarnings("unchecked")
    @Override
    public void process(PrintWriter writer, LineReader reader) {

        try {

            writer.write(INFO);
            String user = CommandUtils.getData("Podaj uzytkownika", writer, reader);
            String pass = CommandUtils.getData("Podaj haslo", writer, reader, '*' );
            writer.write("-----------------------------------------------\n");
            String ip = CommandUtils.getData("Adres", writer, reader);
            String port = CommandUtils.getData("Port", writer, reader);
            writer.write("-----------------------------------------------\n");
            writer.flush();

            Validator validator = (Validator) Naming.lookup(LoginServer.REMOTE_METHOD_NAME);
            String sidInBase64  = validator.authorize(user,pass);

            writer.write("\tAutoryzacja uzytkownika...Sukces\n");

            List<AbstractWorker> workerList = getOutputData(ip, Integer.valueOf(port), sidInBase64);
            writer.write("\tUstanawianie polaczenia...Sukces\n");
            writer.write("\tPobieranie...Sukces\n");

            writer.flush();
            writer.write("-----------------------------------------------\n");

            String maybeCharacter = CommandUtils.getData("Czy zapisac pobrane dane [T]/[N]", writer, reader);
            if ( maybeCharacter.equalsIgnoreCase("t") ) {

                try {
                    CommandUtils.serializeWorkerList(workerList);
                    writer.write("\tZapisywanie...Sukces\n");
                    writer.flush();
                } catch ( IOException e ){
                    writer.write("\tZapisywanie...Blad\n");
                    writer.flush();
                }
            }

        } catch (RemoteException e) {

            writer.write("\tAutoryzacja uzytkownika...Blad\n");
        } catch (NotBoundException e) {

            writer.write("\tNie mozna polaczyc sie z serwisem autoryzujacym\n");
        } catch ( ConnectException e) {

            writer.write("\tUstanawianie polaczenia...Blad\n");
        } catch (Exception e) {

            writer.write("\tWystapil nie oczekiwany blad\n");
            logger.error("Wystapil nieoczekiwany blad", e);
        } finally {
            writer.flush();
        }
    }


    @SuppressWarnings("unchecked")
    private List<AbstractWorker> getOutputData(String host, Integer port, String sid)
            throws IOException {

        Socket
            client = new Socket();
            client.connect(new InetSocketAddress(host,port),10);

        if(!client.isConnected()) {
            throw new ConnectException();
        }

        DataOutputStream
            dos = new DataOutputStream(client.getOutputStream());
            dos.writeUTF(
                PARAM_TOKEN.concat("=").concat(sid)
                .concat("&")
                .concat(PARAM_FRAME).concat("=").concat(FrameType.F_GET_ALL.getFrameName()));

            dos.flush();

        DataInputStream dis   = new DataInputStream(client.getInputStream());
        ObjectInputStream ois = new ObjectInputStream(dis);

        List<AbstractWorker> workerList = new ArrayList<>();
        try {
            workerList = (List<AbstractWorker>) ois.readObject();
        } catch ( Exception e ) {
            logger.error("An error occur while trying to deserialize object", e );
        }

        dis.close();
        ois.close();

        return workerList;
    }


}
