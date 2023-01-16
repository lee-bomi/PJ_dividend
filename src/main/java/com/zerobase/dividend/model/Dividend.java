package com.zerobase.dividend.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class Dividend {

    private LocalDateTime date;
    private String dividend;
}
