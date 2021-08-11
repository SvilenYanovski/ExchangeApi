package com.yanovski.exchangeapi.dto;

import com.yanovski.exchangeapi.entities.ApiCurrencyRate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@ApiModel
@AllArgsConstructor
@NoArgsConstructor
public class RateDto {
    @ApiModelProperty("currency ISO 3-letter code")
    private String code;
    @ApiModelProperty("currency rate value related to EUR")
    private Double rate;
    @ApiModelProperty("currency rate timestamp")
    private LocalDateTime timestamp;

    public RateDto(ApiCurrencyRate entity) {
        this.code = entity.getCurrency().getCode();
        this.rate = entity.getRate();
        this.timestamp = entity.getCreatedAt();
    }
}
