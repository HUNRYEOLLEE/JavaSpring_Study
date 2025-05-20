package com.korit.springboot_study.dto;

import lombok.Data;

import java.util.List;

@Data
public class PostData {
    private String name;
    private Integer age;
    private Address address;
}
