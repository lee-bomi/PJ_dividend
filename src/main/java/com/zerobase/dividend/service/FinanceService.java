package com.zerobase.dividend.service;

import com.zerobase.dividend.exception.impl.NoCompanyException;
import com.zerobase.dividend.model.Company;
import com.zerobase.dividend.model.Dividend;
import com.zerobase.dividend.model.ScrapedResult;
import com.zerobase.dividend.model.constants.CacheKey;
import com.zerobase.dividend.persist.CompanyRepository;
import com.zerobase.dividend.persist.DividendRepository;
import com.zerobase.dividend.persist.entity.CompanyEntity;
import com.zerobase.dividend.persist.entity.DividendEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class FinanceService {

    private final CompanyRepository companyRepository;
    private final DividendRepository dividendRepository;

    @Cacheable(key = "#companyName", value = CacheKey.KEY_FINANCE)
    public ScrapedResult getDividendByCompanyName(String companyName) {
        log.info("search company -> " + companyName);
        //회사명이 db에 있는지확인
        CompanyEntity companyEntity = companyRepository.findByName(companyName)
                .orElseThrow(() -> new NoCompanyException());

        //그 회사의 배당금정보를 조회
        List<DividendEntity> dividendEntity = dividendRepository.findAllByCompanyId(companyEntity.getId());
        List<Dividend> dividends = new ArrayList<>();

        for (DividendEntity i : dividendEntity) {
            dividends.add(Dividend.from(i));
        }

        return ScrapedResult.builder()
                .company(Company.from(companyEntity))
                .dividendEntities(dividends)
                .build();
    }
}
