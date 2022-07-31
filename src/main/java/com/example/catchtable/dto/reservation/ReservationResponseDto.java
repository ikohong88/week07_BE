package com.example.catchtable.dto.reservation;

import com.example.catchtable.model.Reservation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationResponseDto {
    private Long id;
//    private String userId;
    private Long storeId;
    private Date date;
    private Integer members; // member? members?

    public ReservationResponseDto(Reservation reservation) {
        this.id = reservation.getId();
        this.storeId = reservation.getStoreId();
        this.date = reservation.getDate();
        this.members = reservation.getMembers();
    }
}
