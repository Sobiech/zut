package pl.zut.zjava.commands;

import org.jline.reader.LineReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.zut.zjava.commons.utils.CommandUtils;
import pl.zut.zjava.entity.Worker;
import pl.zut.zjava.entity.service.WorkerService;
import pl.zut.zjava.entity.service.impl.WorkerServiceImpl;

import java.io.PrintWriter;
import java.util.Objects;

public class RemoveWorkerCommand implements ICommand {

    private static final Logger logger = LoggerFactory.getLogger(RemoveWorkerCommand.class);

    private static final String INFO =
            "\t\n3 Usun pracownika\n" +
                    "-----------------------------------------------\n";
    @Override
    public void process(PrintWriter writer, LineReader reader) {

        try {

            writer.write(INFO);

            String workerId = getData("Podaj Identyfikator", writer, reader);
            WorkerService service =
                new WorkerServiceImpl();

            Worker abstractWorker = service.findById(workerId);
            if (Objects.isNull(abstractWorker) ){
                writer.write("\n\tPracownik o podanym id:" + workerId + " nie istnieje\n");
                writer.flush();
                return;
            }


            writer.write(abstractWorker.getFullData());
            writer.flush();

            String maybeCharacter = CommandUtils.getData("Usunac pracownika [T]/[N]", writer, reader);
            if ( maybeCharacter.equalsIgnoreCase("t") ) {

                service.removeAndFlush(abstractWorker);
                writer.write("\n\tPracownik zostal usuniety z bazy.\n");
                writer.flush();
            }
        } catch (IllegalArgumentException e ) {

            logger.error("Wystapil blad podczas wprowadzania danych", e);
            writer.write("Podano niepoprawne dane\n");
            writer.flush();
        }
    }

    private String getData(String desc, PrintWriter writer, LineReader reader)
            throws IllegalArgumentException {

        return CommandUtils.getData(desc,writer,reader);
    }
}
