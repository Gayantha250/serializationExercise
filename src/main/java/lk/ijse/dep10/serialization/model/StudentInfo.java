package lk.ijse.dep10.serialization.model;

import java.io.Serializable;

public class StudentInfo implements Serializable {

    public String id;
    public String name;
    public String address;

    public StudentInfo(String id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public StudentInfo() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
