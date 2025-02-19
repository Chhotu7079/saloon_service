package com.saloon.modal;

import lombok.Data;

@Data
public class SaloonReport {

    private Long saloonId;
    private String saloonName;
    private int totalEarnings;
    private Integer totalBookings;
    private Integer cancelledBookings;
    private Double totalRefund;

}
