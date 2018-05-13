package pl.zut.zjava.commands;

import org.jline.reader.LineReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.zut.zjava.commons.enums.ProtocolType;
import pl.zut.zjava.commons.utils.CommandUtils;
import pl.zut.zjava.entity.Worker;
import pl.zut.zjava.server.connection.rmi.LoginServer;
import pl.zut.zjava.server.connection.rmi.Validator;
import pl.zut.zjava.server.strategy.ConnectionStrategy;
import pl.zut.zjava.server.strategy.SoapStrategy;
import pl.zut.zjava.server.strategy.TcpStrategy;

import java.io.*;
import java.net.ConnectException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;


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

            String choosenProtocol = CommandUtils.getData("Protokol [T]CP/IP czy [S]OAP?", writer, reader);
            ProtocolType protocolType = ProtocolType.getProtocolTypeByAbbrevation(choosenProtocol);
            String ip = CommandUtils.getData("Adres", writer, reader);
            String port = CommandUtils.getData("Port", writer, reader);

            String endpoint = null;
            if ( protocolType.equals(ProtocolType.SOAP)) {
                endpoint = CommandUtils.getData("Zasob", writer, reader);
            }

            writer.write("-----------------------------------------------\n");
            writer.flush();

            Validator validator = (Validator) Naming.lookup(LoginServer.REMOTE_METHOD_NAME);
            String sidInBase64  = validator.authorize(user,pass);

            writer.write("\tAutoryzacja uzytkownika...Sukces\n");

            ConnectionStrategy conn = getStrategyByProtocol(protocolType, endpoint);
            List<Worker> workerList = conn.getWorkerList(ip, Integer.valueOf(port), sidInBase64);

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


    private ConnectionStrategy getStrategyByProtocol(ProtocolType protocolType, String endpoint) {

        if ( protocolType.equals(ProtocolType.TCP_IP)) {
            return new TcpStrategy();
        } else {
            return new SoapStrategy(endpoint);
        }
    }



}
