package com.korit.springboot_study.dto;

import lombok.Data;

import java.util.List;

@Data
public class Address {
    private  String address1;
    private  String address2;
    private  String address3;
    private List<String> address4;
}

