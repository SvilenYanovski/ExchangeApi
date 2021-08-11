package com.yanovski.exchangeapi.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiCurrencyConversion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String queryFrom;

    @Column
    private String queryTo;

    @Column
    private String queryAmount;

    @Column
    private String queryFromRateValue;

    @Column
    private Long queryFromRateId;

    @Column
    private String queryToRateValue;

    @Column
    private Long queryToRateId;

    @Column
    private String calculatedCrossRate;

    @Column
    private String calculatedResult;

    @Column
    private LocalDateTime createdAt;
}
