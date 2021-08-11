package com.yanovski.exchangeapi.dto;

import com.yanovski.exchangeapi.entities.ApiCurrency;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@ApiModel
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyDto {
    @ApiModelProperty("currency ISO 3-letter code")
    private String code;
    @ApiModelProperty("currency legal name")
    private String name;

    public CurrencyDto(ApiCurrency entity) {
        this.code = entity.getCode();
        this.name = entity.getName();
    }
}
