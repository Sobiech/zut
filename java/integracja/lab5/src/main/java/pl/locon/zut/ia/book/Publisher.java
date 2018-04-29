package pl.locon.zut.ia.book;

import javax.xml.bind.annotation.XmlType;

@XmlType( name = "publisher")
public class Publisher {


    private String name;

    private String url;


    @Override
    public String toString() {
        return "Publisher{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
