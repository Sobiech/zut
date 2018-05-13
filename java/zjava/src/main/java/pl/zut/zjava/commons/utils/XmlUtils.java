package pl.zut.zjava.commons.utils;

import pl.zut.zjava.commons.jaxb.SchemaResolver;
import pl.zut.zjava.commons.dto.WorkerListDto;

import javax.xml.bind.*;
import java.io.*;

public final class XmlUtils {


    public static <E> E UnmarshallDataFromString(String xmlString, Class<E> eClass)
            throws JAXBException, IOException {

        JAXBContext ctx = JAXBContext.newInstance(eClass);
        Unmarshaller contextUnmarshaller =ctx.createUnmarshaller();

        StringReader sr = new StringReader(xmlString);

        return (E) contextUnmarshaller.unmarshal(sr);
    }


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


    public static <E> String MarshalDataToString(Class<E> clazz, E data)
            throws JAXBException {

        JAXBContext ctx = JAXBContext.newInstance(clazz);
        Marshaller marshaller = ctx.createMarshaller();

        Writer writer = new StringWriter();
        marshaller.marshal(data, writer);

        return writer.toString();
    }


    public static <E> void MarshalDataToFile(String filePath, Class<E> clazz, E data )
            throws JAXBException, IOException {

        JAXBContext ctx = JAXBContext.newInstance(clazz);
        Marshaller marshaller = ctx.createMarshaller();

        FileWriter fileWriter = new FileWriter(filePath);
        marshaller.marshal(data, fileWriter);
    }



    public static <E> void ResolveSchemaBy(Class<E> clazz, String schemaPath)
            throws IOException, JAXBException {

        // create new JAXB context
        JAXBContext context = JAXBContext.newInstance(WorkerListDto.class);

        // create new schema out put resolver
        SchemaOutputResolver sor = new SchemaResolver();

        // generate schema
        context.generateSchema(sor);
    }

}
