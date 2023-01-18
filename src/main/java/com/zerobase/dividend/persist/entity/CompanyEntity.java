package com.zerobase.dividend.persist.entity;

import com.zerobase.dividend.model.Company;
import lombok.*;

import javax.annotation.security.DenyAll;
import javax.persistence.*;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="COMPANY")
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String ticker;

    public static CompanyEntity of(Company company) {
        return CompanyEntity.builder()
                .name(company.getName())
                .ticker(company.getTicker())
                .build();
    }
}
