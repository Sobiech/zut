package pl.zut.zjava.commands;

import org.jline.reader.LineReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.zut.zjava.commons.enums.PositionType;
import pl.zut.zjava.commons.utils.CommandUtils;
import pl.zut.zjava.entity.Worker;
import pl.zut.zjava.entity.Director;
import pl.zut.zjava.entity.Trader;
import pl.zut.zjava.entity.service.AbstractDbService;
import pl.zut.zjava.entity.service.impl.DirectorServiceImpl;
import pl.zut.zjava.entity.service.impl.TraderServiceImpl;
import pl.zut.zjava.entity.service.impl.WorkerServiceImpl;

import java.io.PrintWriter;
import java.math.BigDecimal;

public class AddWorkerCommand implements ICommand {

    private static final Logger logger = LoggerFactory.getLogger(AddWorkerCommand.class);

    private static final String INFO =
            "\t\n2 Dodaj pracownika\n" +
                    "-----------------------------------------------\n";

    @SuppressWarnings("unchecked")
    @Override
    public void process(final PrintWriter writer, final LineReader reader) {

        try {

            writer.write(INFO);

            AbstractDbService service;
            Worker worker;
            writer.write("Wprowadz dane: \n");
            writer.flush();

            String pesel = getData("Pesel", writer, reader);
            String imie = getData("Imie", writer, reader);
            String nazwisko = getData("Nazwisko", writer, reader);
            BigDecimal wynagrodzenie = BigDecimal.valueOf(Float.valueOf(getData("Wynagrodzenie", writer, reader)));
            PositionType positionType = PositionType.valueOf(getData("Stanowisko: PRACOWNIK / HANDLOWIEC / DYREKTOR", writer, reader));
            String telefon = getData("Telefon", writer, reader);

            if ( positionType.equals(PositionType.HANDLOWIEC)) {

                service = new TraderServiceImpl();
                worker = new Trader();

                ((Trader) worker).setComissionLimit(Integer.valueOf(getData("Limit prowizji", writer, reader)));
                ((Trader) worker).setComissionRate(BigDecimal.valueOf(Float.valueOf(getData("Stawka prowizji", writer, reader))));
            } else if ( positionType.equals(PositionType.DYREKTOR)) {

                service = new DirectorServiceImpl();
                worker = new Director();

                ((Director) worker).setCostLimit(Integer.valueOf(getData("Limit kosztow", writer, reader)));
                ((Director) worker).setBusinessCard(getData("Karta sluzbowa", writer, reader));
                ((Director) worker).setBusinessAllowance(BigDecimal.valueOf(Float.valueOf(getData("Dodatek sluzbowy", writer, reader))));
            } else {

                service = new WorkerServiceImpl();
                worker = new Worker();
            }

            worker.setPesel(pesel);
            worker.setFirstName(imie);
            worker.setLastName(nazwisko);
            worker.setSalary(wynagrodzenie);
            worker.setPhone(telefon);

            service.saveAndFlush(worker);
            writer.write("\n\tPracownik zostal zapisany poprawnie.\n" );
            writer.flush();

        } catch ( Exception e ) {

            logger.error("Wystapil blad podczas dodawania pracownika", e);
            writer.println("Wystapil blad podczas wprowadzania danych, sprobuj ponownie");
            writer.flush();
        }
    }

    private String getData(String desc, PrintWriter writer, LineReader reader)
            throws IllegalArgumentException {

        return CommandUtils.getData(desc,writer,reader);
    }

}
