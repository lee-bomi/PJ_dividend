package com.zerobase.dividend.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;

@Builder
@Data
public class Company {

    private String name;
    private String ticker;
}
