package es.iesfranciscodelosrios.com.juancarlos.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "config")
public class ConfigDB {
    private String url;
    private String user;
    private String password;


    // Getters and Setters

    @XmlElement
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @XmlElement
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @XmlElement
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

