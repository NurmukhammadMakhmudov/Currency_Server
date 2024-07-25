//package com.example.currency_server.controller;
//
//import com.example.currency_server.model.Currency;
//import com.example.currency_server.parser.CurrencyRateParserXml;
//import com.example.currency_server.requester.CbrRequesterImpl;
//import com.example.currency_server.service.CurrencyRateService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.kafka.support.SendResult;
//import org.springframework.util.concurrent.ListenableFuture;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.time.LocalDate;
//import java.time.Month;
//import java.util.List;
//
//@RestController
//public class Controller {
//    private final CbrRequesterImpl cbrRequester;
//    private final CurrencyRateParserXml currencyRateParserXml;
//    private final CurrencyRateService currencyRateService;
//    private final KafkaTemplate<String, List<Currency>> kafkaTemplate;
//
//    @Autowired
//    public Controller(CbrRequesterImpl cbrRequester, CurrencyRateParserXml currencyRateParserXml, CurrencyRateService currencyRateService, KafkaTemplate<String, List<Currency>> kafkaTemplate) {
//        this.cbrRequester = cbrRequester;
//        this.currencyRateParserXml = currencyRateParserXml;
//        this.currencyRateService = currencyRateService;
//        this.kafkaTemplate = kafkaTemplate;
//    }
//
//    @GetMapping("/page")
//    public List<Currency> GetPage(){
////        LocalDate localDate = LocalDate.of(2021, Month.APRIL,1);
////        String XmlAsString = cbrRequester.getRatesAsXml("https://cbr.ru/scripts/XML_daily.asp");
////        List<Currency> list = currencyRateParserXml.parse(XmlAsString);
//        List<Currency> c = currencyRateService.getCurrencyRate();
//        System.out.println();
//        ListenableFuture<SendResult<String, List<Currency>>> future = kafkaTemplate.send("Cur", "key", c);
//        future.addCallback(System.out::println, System.err::println);
//        kafkaTemplate.flush();
//       return   c;
//    }
//
//
//}
//
//
