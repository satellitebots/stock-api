package com.alwaysup.stock.stockApi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alwaysup.stock.stockApi.service.StockService;
import com.alwaysup.stock.stockApi.serviceImpl.StockServiceImpl;
import com.alwaysup.stock.stockApi.util.Verifier;

@Configuration
public class BeanConfig {
	@Bean
	public StockService getStockService() {
		return new StockServiceImpl();
	}

	@Bean
	public Verifier getVerifier() {
		return new Verifier();
	}
}
