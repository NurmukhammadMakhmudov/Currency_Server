package com.example.currency_server.service;


import com.currencyServer.grpc.Currency;
import com.example.currency_server.parser.CurrencyRateParser;
import com.example.currency_server.requester.CbrRequester;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CurrencyRateService  extends com.currencyServer.grpc.CurrencyServerGrpc.CurrencyServerImplBase {
    public static final String DATE_FORMAT = "dd/MM/yyyy";
    public final String url = "https://cbr.ru/scripts/XML_daily.asp";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);

    private final CbrRequester cbrRequester;
    private final CurrencyRateParser currencyRateParser;



    public List<com.currencyServer.grpc.Currency> getCurrencyRate(LocalDate date) {
        List<com.currencyServer.grpc.Currency> rates;

            String urlWithParams = String.format("%s?date_req=%s", url, DATE_FORMATTER.format(date));
            String  ratesAsXml = cbrRequester.getRatesAsXml(urlWithParams);
            rates = currencyRateParser.parse(ratesAsXml);


        return rates;
    }
    public List<com.currencyServer.grpc.Currency> getCurrencyRate() {
        List<Currency> rates;

        String ratesAsXml = cbrRequester.getRatesAsXml(url);
        rates = currencyRateParser.parse(ratesAsXml);


        return rates;
    }

}