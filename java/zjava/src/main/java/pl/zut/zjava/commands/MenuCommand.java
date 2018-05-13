package pl.zut.zjava.commands;

import org.apache.commons.lang3.math.NumberUtils;
import org.jline.reader.LineReader;
import org.jline.reader.impl.LineReaderImpl;
import pl.zut.zjava.commons.utils.CommandUtils;
import pl.zut.zjava.entity.Worker;
import pl.zut.zjava.entity.service.impl.WorkerServiceImpl;

import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

public class MenuCommand implements ICommand {

    private final String menuData =
            "Wybierz jedna z ponizych opcji:\n" +
                    "\t1. Lista pracownikow\n" +
                    "\t2. Dodaj pracownika\n" +
                    "\t3. Usun pracownika\n" +
                    "\t4. Kopia zapasowa\n" +
                    "\t5. Pobierz dane z sieci\n";


    @Override
    public void process(final PrintWriter writer, final LineReader reader) {

        writer.write(menuData);
        writer.flush();

        writer.write("Wybor> ");
        writer.flush();

        Optional<String> maybeCharacter =
            CommandUtils.readCharacter(reader,writer);

        if ( !maybeCharacter.isPresent() )
            return;

        if ( NumberUtils.isCreatable(maybeCharacter.get()) ) {
            Integer choose = Integer.valueOf(maybeCharacter.get());
            ICommand cmd = null;

            switch (choose){
                case 1:
                    processReadWorkerList(writer, reader);
                    break;
                case 2:
                    cmd = new AddWorkerCommand();
                    break;
                case 3:
                    cmd = new RemoveWorkerCommand();
                    break;
                case 4:
                    cmd = new BackupCommand();
                    break;
                case 5:
                    cmd = new DataNetworkDownloadCommand();
                    break;
            }

            if ( cmd != null ) {
                cmd.process(writer, reader);
            }
        }
    }


    private void processReadWorkerList(final PrintWriter writer, final LineReader reader ) {

        Integer limit = Integer.valueOf(CommandUtils.getData("Podaj limit", writer, reader));
        List<Worker> workerList = new WorkerServiceImpl().getList(limit);

        int pozycja = 0;

        do {

            writer.write("\n1. Lista pracownikow\n");
            writer.write(workerList.get(pozycja).toString());
            writer.write("\n                    [ Pozycja " + ( pozycja + 1 ) + "/" + workerList.size() + " ] ");
            writer.write("\n[ N ] : nastepny \n");
            writer.write("[ P ] : poprzedni \n");
            writer.write("[ Q ] : wroc \n");

            writer.flush();

            int c = ((LineReaderImpl) reader).readCharacter();
            if ( c == 112 ) {
                pozycja -= 1;
            } else if ( c == 110 ) {
                pozycja += 1;
            } else if ( c == 113) {
                break;
            }
        } while (pozycja < workerList.size() && pozycja >= 0 );
    }
}
