package com.parking.lot.model.account.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Address {
    String addressLine1;
    String addressLine2;
    String addressLine3;
    String country;
    String state;
    String city;
    String zip;
}

