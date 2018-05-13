package pl.zut.zjava.commands;

import org.jline.reader.LineReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.zut.zjava.entity.AbstractWorker;
import pl.zut.zjava.entity.service.impl.AbstractWorkerServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

public class BackupCommand implements ICommand {

    private static final Logger logger = LoggerFactory.getLogger(BackupCommand.class);

    private static final String INFO =
            "\t\n4 Kopia zapasowa\n" +
                    "-----------------------------------------------\n";

    @Override
    public void process(PrintWriter writer, LineReader reader) {

        writer.write(INFO);
        String action = CommandUtils.getData("[Z]achowaj/[O]dwtorz", writer, reader);

        if ( action.equalsIgnoreCase("z") ) {

            try {
                Optional<String> maybeFileName = CommandUtils.serializeWorkerList(new AbstractWorkerServiceImpl().getList(300));
                if ( maybeFileName.isPresent()) {
                    writer.write("-----------------------------------------------\n");
                    writer.write("\tNazwa pliku : \t " + maybeFileName.get());
                } else {
                    writer.write("\n\tNie wybrano nazwy pliku\n");
                }
            } catch (IOException e) {
                writer.write("\n\tNie udalo sie zapisac danych\n");
                logger.error("An error occur while trying to save data", e);
            } finally {
                writer.flush();
            }

        } else if ( action.equalsIgnoreCase("o")) {

            try {
                List<AbstractWorker> workerList = CommandUtils.deserializeWorkerList();
                writer.write("\n\tDane zostaly odczytane poprawnie\n");
            } catch (IOException | ClassNotFoundException e) {

                writer.write("\n\tNie udalo sie odczytac danych\n");
                logger.error("An error occur while trying to load data", e);
            } finally {
                writer.flush();
            }
        }
    }
}
