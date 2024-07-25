package com.example.currency_server.parser;

import com.example.currency_server.model.Currency;

import java.util.List;

public interface CurrencyRateParser {

    List<com.currencyServer.grpc.Currency> parse(String ratesAsString);
}