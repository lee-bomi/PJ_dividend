package com.zerobase.dividend.scraper;

import com.zerobase.dividend.model.Company;
import com.zerobase.dividend.model.ScrapedResult;

public interface Scraper {

    /**
     * 회사정보로 배당금정보를 가져온다
     * @param company
     * @return
     */
    ScrapedResult scrap(Company company);

    /**
     * 회사명으로 회사정보를 가져온다
     * @param ticker
     * @return
     */
    Company scrapCompanyByTicker(String ticker);


}
