package com.zerobase.dividend.persist.entity;

import com.zerobase.dividend.model.Dividend;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor
@Entity(name="DIVIDEND")
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"companyId", "date"} //두 컬럼의 값이 동일하다면 값을 새로 저장하지않게함
                )
        }
)
public class DividendEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long companyId;
    private LocalDateTime date;
    private String dividend;

    public DividendEntity(Long id, Dividend dividend) {
        this.companyId = id;
        this.date = dividend.getDate();
        this.dividend = dividend.getDividend();
    }
}
