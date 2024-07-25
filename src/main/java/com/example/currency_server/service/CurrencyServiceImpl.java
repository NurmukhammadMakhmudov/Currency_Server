package com.example.currency_server.service;

import com.currencyServer.grpc.Currency;
import com.currencyServer.grpc.CurrencyRequest;
import com.currencyServer.grpc.CurrencyResponse;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyServiceImpl extends com.currencyServer.grpc.CurrencyServerGrpc.CurrencyServerImplBase {

    @Autowired
    private CurrencyRateService currencyRateService;


    @Override
    public void cur(CurrencyRequest request, StreamObserver<CurrencyResponse> responseObserver) {

        CurrencyResponse currencyResponse = CurrencyResponse.newBuilder()
                .addAllCurrency(currencyRateService.getCurrencyRate(LocalDate.parse(request.getDate())))
                .build();
        responseObserver.onNext(currencyResponse);
        responseObserver.onCompleted();
    }
}
