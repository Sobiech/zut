package pl.locon.zut.ia.manager;

import pl.locon.zut.ia.xml.XmlUtils;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public abstract class AbstractDaoManager<E, T> {

    protected List<E> dataList;

    private Class<T> clazz;

    protected AbstractDaoManager(Class<T> clazz) {
        this.clazz = clazz;
    }


    @SuppressWarnings("JavaReflectionInvocation")
    public void saveDataList() throws Exception {
        T instance = clazz.getDeclaredConstructor().newInstance(this.dataList);
        XmlUtils.MarshalDataToFile(getFileUrl(), clazz, instance);
    }


    public T getData() throws JAXBException, IOException {
        return XmlUtils.UnmarshallDataFromFile(getFileUrl(), clazz);
    }


    protected abstract String getFileUrl();

}
