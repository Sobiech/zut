package pl.zut.zjava.jaxb;

import javax.xml.bind.SchemaOutputResolver;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

public class SchemaResolver extends SchemaOutputResolver {



    @Override
    public Result createOutput(String namespaceUri, String suggestedFileName) throws IOException {

        // create new file
        File file = new File("xml/dataList.xsd");

        // create stream result
        StreamResult result = new StreamResult(file);

        // set system id
        result.setSystemId(file.toURI().toURL().toString());

        // return result
        return result;
    }
}
