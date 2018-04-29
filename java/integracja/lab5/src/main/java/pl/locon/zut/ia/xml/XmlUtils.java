package pl.locon.zut.ia.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public final class XmlUtils {


    @SuppressWarnings("unchecked")
    public static <E> E UnmarshallDataFromFile(String filePath, Class<E> eClass)
            throws JAXBException, IOException {

        JAXBContext ctx = JAXBContext.newInstance(eClass);
        Unmarshaller contextUnmarshaller =ctx.createUnmarshaller();

        FileReader fileReader = new FileReader(filePath);
        E output = (E) contextUnmarshaller.unmarshal(fileReader);
        fileReader.close();

        return output;
    }


    public static <E> void MarshalDataToFile(String filePath, Class<E> clazz, E data )
            throws JAXBException, IOException {

        JAXBContext ctx = JAXBContext.newInstance(clazz);
        Marshaller marshaller = ctx.createMarshaller();

        FileWriter fileWriter = new FileWriter(filePath);
        marshaller.marshal(data, fileWriter);
    }

}
