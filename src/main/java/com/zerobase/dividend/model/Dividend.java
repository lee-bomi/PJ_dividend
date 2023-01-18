package com.zerobase.dividend.model;

import com.zerobase.dividend.persist.entity.DividendEntity;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class Dividend {

    private LocalDateTime date;
    private String dividend;

    public static Dividend from(DividendEntity entity) {
        return Dividend.builder()
                .date(entity.getDate())
                .dividend(entity.getDividend())
                .build();
    }
}
