package com.example.currency_server.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Currency   {

    String numCode;
    String charCode;
    String nominal;
    String name;
    String value;

}
