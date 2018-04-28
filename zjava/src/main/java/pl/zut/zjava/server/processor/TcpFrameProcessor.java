package pl.zut.zjava.server.processor;

import pl.zut.zjava.commons.Frame;
import pl.zut.zjava.commons.FrameType;
import pl.zut.zjava.entity.Dyrektor;
import pl.zut.zjava.entity.Handlowiec;
import pl.zut.zjava.entity.Pracownik;
import pl.zut.zjava.entity.service.DyrektorServiceImpl;
import pl.zut.zjava.entity.service.HandlowiecServiceImpl;
import pl.zut.zjava.entity.service.PracownikServiceImpl;

import java.sql.SQLException;

public class TcpFrameProcessor implements FrameProcessor<FrameType, String>{


    public static final String CLIENT_WELCOME =
            "Dostepne komendy:\n" +
                    " - F_WORKER_LIST: Lista pracownikow \n" +
                    " - F_HANDLER_LIST: Lista handlowcow \n" +
                    " - F_DIRECTOR_LIST: Lista dyrektorow \n" +
                    " - F_HELP: Drukuje powyzsza liste\n\n";


    @Override
    public String processFrame(FrameType inputFrame) {

        try {

            StringBuilder result = new StringBuilder();

            if ( inputFrame.equals(FrameType.F_GET_HANDLER_LIST) ){
                new HandlowiecServiceImpl().getList().stream().map(Handlowiec::getFullData).forEach(result::append);
            } else if ( inputFrame.equals(FrameType.F_GET_WORKER_LIST) ) {
                new PracownikServiceImpl().getList().stream().map(Pracownik::getFullData).forEach(result::append);
            } else if ( inputFrame.equals(FrameType.F_GET_DIRECTOR_LIST ) ){
               new DyrektorServiceImpl().getList().stream().map(Dyrektor::getFullData).forEach(result::append);
            } else if ( inputFrame.equals(FrameType.F_HELP) ) {
                result.append(CLIENT_WELCOME);
            }

            return result.toString();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


}
