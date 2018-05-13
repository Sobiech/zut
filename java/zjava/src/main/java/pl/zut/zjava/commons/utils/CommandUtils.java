package pl.zut.zjava.commons.utils;

import com.google.common.base.Strings;
import org.jline.keymap.KeyMap;
import org.jline.reader.LineReader;
import org.jline.reader.UserInterruptException;
import org.jline.reader.impl.LineReaderImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.zut.zjava.entity.Worker;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CommandUtils {

    private static final Logger logger = LoggerFactory.getLogger(CommandUtils.class);


    public static Optional<String> readCharacter(final LineReader reader, final PrintWriter writer) {
        StringBuilder sb = new StringBuilder();

        int c = ((LineReaderImpl) reader).readCharacter();
        if (c == 10 || c == 13)
            return Optional.empty();

        sb.append(new String(Character.toChars(c)));

        return Optional.of(sb.toString());
    }


    public static Optional<String> readLine(final LineReader reader, final PrintWriter writer) {

        String line = reader.readLine();
        line = line.trim();

        if (Strings.isNullOrEmpty(line)) {
            return Optional.empty();
        }

        writer.println(KeyMap.display(line));
        return Optional.of(line);
    }



    public static String getData(String desc, final PrintWriter writer, final LineReader reader)
            throws IllegalArgumentException {

        writer.write("\t".concat(desc).concat(" : "));
        writer.flush();

        String line = null;
        while (  Strings.isNullOrEmpty(line) ) {
            try {
                line = reader.readLine();
            } catch ( UserInterruptException e ){
                break;
            }
        }

        if (Strings.isNullOrEmpty(line)) {
            throw new IllegalArgumentException("Nie podano poprawnych danych !");
        }

        return line;
    }



    public static String getData(String desc, final PrintWriter writer, final LineReader reader, Character mask)
            throws IllegalArgumentException {

        writer.write("\t".concat(desc).concat(" : "));
        writer.flush();

        String line = null;
        while (  Strings.isNullOrEmpty(line) ) {
            try {
                line = reader.readLine(mask);
            } catch ( UserInterruptException e ){
                break;
            }
        }

        if (Strings.isNullOrEmpty(line)) {
            throw new IllegalArgumentException("Nie podano poprawnych danych !");
        }

        return line;
    }



    public static Optional<String> serializeWorkerList(List<Worker> data)
            throws IOException {

        JFileChooser fileChooser = new JFileChooser();
        Optional<String> maybeFileName = Optional.empty();
        if (fileChooser.showSaveDialog(new JFrame()) == JFileChooser.APPROVE_OPTION) {

            File file = fileChooser.getSelectedFile();

            logger.info("Saving data: to file: {}", file.getAbsolutePath());

            maybeFileName = Optional.of(file.getName());
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
            out.writeObject(data);
            out.close();
        }

        return maybeFileName;
    }


    @SuppressWarnings("unchecked")
    public static List<Worker> deserializeWorkerList()
            throws IOException, ClassNotFoundException {

        JFileChooser fileChooser = new JFileChooser();
        List<Worker> workerList = new ArrayList<>();
        if ( fileChooser.showOpenDialog(new JFrame()) == JFileChooser.APPROVE_OPTION ) {

            File file = fileChooser.getSelectedFile();
            logger.info("Loading data: from file: {}", file.getAbsolutePath());

            ObjectInputStream is = new ObjectInputStream(new FileInputStream(file));
            workerList = (List<Worker>) is.readObject();
            is.close();
        }

        return workerList;
    }

}
