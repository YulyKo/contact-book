package com.example.demo.domain;

import java.sql.Date;

public class ContactCreateResponse extends Contact {
    private String name;
    private String phone;
    private Date dateOfBirth;

    public ContactCreateResponse(String name, String phone, Date dateOfBirth) {
        this.name = name;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
    }
}
