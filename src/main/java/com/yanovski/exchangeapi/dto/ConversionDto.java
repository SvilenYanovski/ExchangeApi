package com.yanovski.exchangeapi.dto;

import com.yanovski.exchangeapi.entities.ApiCurrencyConversion;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty("system id")
    private Long id;

    @ApiModelProperty("currency from")
    private String queryFrom;

    @ApiModelProperty("currency to")
    private String queryTo;

    @ApiModelProperty("amount to change")
    private String queryAmount;

    @ApiModelProperty("first symbol rate")
    private String queryFromRateValue;

    @ApiModelProperty("first symbol rate id")
    private Long queryFromRateId;

    @ApiModelProperty("second symbol rate")
    private String queryToRateValue;

    @ApiModelProperty("second symbol rate id")
    private Long queryToRateId;

    @ApiModelProperty("cross rate to convert between the two currencies")
    private String calculatedCrossRate;

    @ApiModelProperty("calculated result")
    private String calculatedResult;

    @ApiModelProperty("conversion time")
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
