package com.yanovski.exchangeapi.dto;

import com.yanovski.exchangeapi.entities.ApiKey;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@ApiModel
@NoArgsConstructor
@AllArgsConstructor
public class ApiKeyDto {
    @ApiModelProperty("system id")
    private Long id;
    @ApiModelProperty("actual  api key")
    private String apiKey;
    @ApiModelProperty("is the api key active or deleted")
    private Boolean active;
    @ApiModelProperty("describes the use of the api key")
    private String description;

    public ApiKeyDto(ApiKey entity) {
        this.id = entity.getId();
        this.active = entity.getActive();
        this.apiKey = entity.getApiKey();
        this.description = entity.getDescription();
    }
}
