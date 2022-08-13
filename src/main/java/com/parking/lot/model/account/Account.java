package com.parking.lot.model.account;

import com.parking.lot.model.account.Contact;
import com.parking.lot.model.account.PersonalInfo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Account {

    private String userName;
    private String password;
    private Contact contact;
    private PersonalInfo personalInfo;
}
