package com.yanovski.exchangeapi.client.api_model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Currency {
    private String code;
    private String name;
}
