package com.parking.lot.model.parking;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter

@NoArgsConstructor
public class ParkingTicket {
    String ticketId;
    String parkingSpotId;
    String licenseId;
    LocalDateTime issuedAt;
    String vacatedAt;
    double charges;
    TicketStatus ticketStatus;
}
