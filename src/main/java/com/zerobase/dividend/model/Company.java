package com.zerobase.dividend.model;

import com.zerobase.dividend.persist.entity.CompanyEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
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
