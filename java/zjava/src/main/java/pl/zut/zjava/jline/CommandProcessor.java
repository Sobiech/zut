package pl.zut.zjava.jline;

import org.jline.reader.LineReader;
import pl.zut.zjava.commands.ICommand;
import pl.zut.zjava.commands.MenuCommand;

import java.io.PrintWriter;
import java.util.Objects;

public class CommandProcessor {

    private final PrintWriter writer;
    private final LineReader reader;

    private static final String DISPLAY_HELP_DATA = "\nDostepne komendy:" +
            "\t\n - menu: pokazuje menu klienta," +
            "\t\n - help: pokazuje ten ekran pomocy" +
            "\t\n - exit: wylacza program" +
            "\n\n";

    public CommandProcessor( final PrintWriter writer, final LineReader reader) {
        this.writer = writer;
        this.reader = reader;
    }

    public void processCommand(final String cmd) {

        ICommand command = null;

        if ( cmd.equals("menu")) {

            command = new MenuCommand();
        } else if ( cmd.equals("help")) {

            this.writer.write(DISPLAY_HELP_DATA);
            this.writer.flush();
        }

        if (Objects.isNull(command) ) {
            return;
        }

        command.process(this.writer,this.reader);
    }

}
