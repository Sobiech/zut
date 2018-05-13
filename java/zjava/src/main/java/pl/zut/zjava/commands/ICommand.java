package pl.zut.zjava.commands;

import org.jline.reader.LineReader;

import java.io.PrintWriter;

public interface ICommand {

    void process(final PrintWriter writer, final LineReader reader);

}
