package com.nttdata.bankproducts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BankProductsApplication {
  public static void main(String[] args) {
    SpringApplication.run(BankProductsApplication.class, args);
  }

}
