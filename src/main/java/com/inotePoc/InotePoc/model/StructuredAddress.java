package com.inotePoc.InotePoc.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StructuredAddress {
    @JsonProperty("Address")
    private String address;

    @JsonProperty("BldgNb")
    private String bldgNb;

    @JsonProperty("Ctry")
    private String ctry;

    @JsonProperty("CtrySubDvsn")
    private String ctrySubDvsn;

    @JsonProperty("Dept")
    private String dept;

    @JsonProperty("Flr")
    private String flr;

    @JsonProperty("Name")
    private String name;

    @JsonProperty("PstBx")
    private String pstBx;

    @JsonProperty("PstCd")
    private String pstCd;

    @JsonProperty("Room")
    private String room;

    @JsonProperty("StrtNm")
    private String strtNm;

    @JsonProperty("TwnNm")
    private String twnNm;
}
