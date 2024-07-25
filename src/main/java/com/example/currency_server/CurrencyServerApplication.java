package com.example.currency_server;

import com.example.currency_server.service.CurrencyServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.FileSystemResource;

import java.io.IOException;

@SpringBootApplication
public class CurrencyServerApplication  implements CommandLineRunner {
    @Autowired
    private  CurrencyServiceImpl currencyService;


    public static void main(String[] args) throws IOException, InterruptedException {

      //  BeanFactory factory = new XmlBeanFactory(new FileSystemResource("beans"));

        SpringApplication.run(CurrencyServerApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception {

        Server server = ServerBuilder.forPort(9092).addService(currencyService)
                .build();
        server.start();
        System.out.println("Server Started");
        server.awaitTermination();
    }
}

