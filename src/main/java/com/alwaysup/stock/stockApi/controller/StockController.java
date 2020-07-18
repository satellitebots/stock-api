package com.alwaysup.stock.stockApi.controller;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.ResponseEntity;

import com.alwaysup.stock.stockApi.model.Stock;
import com.alwaysup.stock.stockApi.service.StockService;
import com.alwaysup.stock.stockApi.util.Verifier;

@Controller
public class StockController {
	
	@Autowired
	StockService stockService;
	
	@Autowired
	Verifier verifier;

	/**
	 * Get all stocks
	 */
	@GetMapping("/stock")
	public List<Stock> getStocks(@RequestParam(value="items", required=false, defaultValue="10") int count,
			@RequestParam(value="page", required=false, defaultValue="10") int page) {
		page = (page<0) ? 0 : page;
		count = (count<1) ? 1 : count;
		Pageable pagedContent = PageRequest.of(page, count);
		return stockService.getStocks(pagedContent);
	}
	
	/**
	 * Get all stocks
	 */
	@GetMapping("/stock/{exchange}")
	public List<Stock> getStocksByExchange(@RequestParam(value="items", required=false, defaultValue="10") int count,
			@RequestParam(value="page", required=false, defaultValue="10") int page,
			@PathVariable String exchange) {
		page = (page<0) ? 0 : page;
		count = (count<1) ? 1 : count;
		Pageable pagedContent = PageRequest.of(page, count);
		return stockService.getStocksByExchange(exchange, pagedContent);
	}

	/**
	 * Add a stock by it's name (item is validated to ensure that such stock symbol exists for mentioned exchange)
	 * otherwise nothing is added and response code is: 1
	 */
	@PostMapping("/stock")
	public Stock addStock(@RequestBody Stock stock) {
		verifier.verifyNotNull(stock);
		verifier.verifyString(stock.getSymbol());
		verifier.verifyString(stock.getFullName());
		verifier.verifyString(stock.getExchange());
		Stock createdStock = stockService.createStock(stock.getSymbol(), stock.getFullName(), stock.getExchange());
		return createdStock;
	}
}

