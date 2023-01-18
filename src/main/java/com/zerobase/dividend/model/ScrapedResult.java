package com.zerobase.dividend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class ScrapedResult {

    private Company company;
    private List<Dividend> dividendEntities;

    public ScrapedResult(){
        this.dividendEntities = new ArrayList<>();
    }
}
