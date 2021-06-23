package com.example.park.controller.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class CardModel {

    private String name;

    private BigDecimal cost;

    @JsonProperty("rentable_after")
    private LocalDate rentableAfter;

    @JsonProperty("rentable_before")
    private LocalDate rentableBefore;

}
