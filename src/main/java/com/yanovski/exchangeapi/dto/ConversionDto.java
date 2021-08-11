package com.yanovski.exchangeapi.dto;

import com.yanovski.exchangeapi.entities.ApiCurrencyConversion;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Data
@Builder
@ApiModel
@NoArgsConstructor
@AllArgsConstructor
public class ConversionDto {
    private Long id;
    private String queryFrom;
    private String queryTo;
    private String queryAmount;
    private String queryFromRateValue;
    private Long queryFromRateId;
    private String queryToRateValue;
    private Long queryToRateId;
    private String calculatedCrossRate;
    private String calculatedResult;
    private LocalDateTime timestamp;

    public ConversionDto(ApiCurrencyConversion entity) {
        this.id = entity.getId();
        this.queryFrom = entity.getQueryFrom();
        this.queryTo = entity.getQueryTo();
        this.queryAmount = entity.getQueryAmount();
        this.queryFromRateValue = entity.getQueryFromRateValue();
        this.queryFromRateId = entity.getQueryFromRateId();
        this.queryToRateValue = entity.getQueryToRateValue();
        this.queryToRateId = entity.getQueryToRateId();
        this.calculatedCrossRate = entity.getCalculatedCrossRate();
        this.calculatedResult = entity.getCalculatedResult();
        this.timestamp = entity.getCreatedAt();
    }
}
