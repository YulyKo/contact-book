package com.example.contactbook.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "contacts")
public class Contact {

    @Id
    @GeneratedValue
    private long id;

    private Date dateOfBirds;
    private String name;
    private String phone;

    public Contact() {}

    public Contact(long id, String name, String phone, Date dateOfBirds) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.dateOfBirds = dateOfBirds;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDateOfBirds() {
        return dateOfBirds;
    }

    public void setDateOfBirds(Date dateOfBirds) {
        this.dateOfBirds = dateOfBirds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
