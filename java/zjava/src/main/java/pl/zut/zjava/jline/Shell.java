package pl.zut.zjava.jline;

import org.jline.reader.*;
import org.jline.reader.impl.DefaultParser;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Shell {

    private static final Logger logger = LoggerFactory.getLogger(Shell.class);

    public Shell() {
        initialize();
    }

    public void initialize() {

        try {

            String prompt = System.getProperty("user.name").concat(">");
            Completer completer;
            Parser parser;

            completer = (reader, line, candidates) -> {
                if (line.wordIndex() == 0) {
                    candidates.add(new Candidate("Command1"));
                } else if (line.words().get(0).equals("Command1")) {
                    if (line.words().get(line.wordIndex() - 1).equals("Option1")) {
                        candidates.add(new Candidate("Param1"));
                        candidates.add(new Candidate("Param2"));
                    } else {
                        if (line.wordIndex() == 1) {
                            candidates.add(new Candidate("Option1"));
                        }
                        if (!line.words().contains("Option2")) {
                            candidates.add(new Candidate("Option2"));
                        }
                        if (!line.words().contains("Option3")) {
                            candidates.add(new Candidate("Option3"));
                        }
                    }
                }
            };

            Terminal terminal = TerminalBuilder.builder()
                .nativeSignals(true)
                .signalHandler(Terminal.SignalHandler.SIG_IGN)
                .build();

            DefaultParser p = new DefaultParser();
            p.setEofOnUnclosedQuote(true);
            parser = p;

            LineReader reader = LineReaderBuilder.builder()
                    .terminal(terminal)
                    .completer(completer)
                    .parser(parser)
                    .build();

            CommandProcessor commandProcessor =
                new CommandProcessor(terminal.writer(), reader);

            while (true) {

                String line = null;
                try {
                    line = reader.readLine(prompt, null, (MaskingCallback) null, null);
                } catch (UserInterruptException e) {
                    // Ignore
                } catch (EndOfFileException e) {
                    return;
                }

                if (line == null) {
                    continue;
                }

                line = line.trim();
                if (line.equalsIgnoreCase("quit") || line.equalsIgnoreCase("exit")) {
                    System.exit(1);
                }

                ParsedLine parsedLine = reader.getParser().parse(line, 0);
                commandProcessor.processCommand(parsedLine.word());
                terminal.flush();
            }
        }
        catch (Throwable t) {
            t.printStackTrace();
        }
    }

}

