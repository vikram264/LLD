package com.parking.lot.model.account;

import com.parking.lot.model.account.common.Address;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Contact {
    String emailId;
    String phone;
    Address address;
}
