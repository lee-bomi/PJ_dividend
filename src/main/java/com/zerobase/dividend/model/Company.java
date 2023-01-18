package com.zerobase.dividend.model;

import com.zerobase.dividend.persist.entity.CompanyEntity;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;

@Builder
@Data
public class Company {

    private String name;
    private String ticker;

    public static Company from(CompanyEntity entity) {
        return Company.builder()
                .name(entity.getName())
                .ticker(entity.getTicker())
                .build();
    }
}
