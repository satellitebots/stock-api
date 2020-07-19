package com.alwaysup.stock.stockApi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

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
	public ResponseEntity<List<Stock>> getStocks(
			@RequestParam(value = "items", required = false, defaultValue = "10") int count,
			@RequestParam(value = "page", required = false, defaultValue = "0") int page) {
		page = (page < 0) ? 0 : page;
		count = (count < 1) ? 1 : count;
		Pageable pagedContent = PageRequest.of(page, count);
		List<Stock> allStocks = stockService.getStocks(pagedContent);
		return new ResponseEntity<>(allStocks, HttpStatus.OK);
	}

	/**
	 * Get all stocks
	 * 
	 * @param count    the total number of items to fetch
	 * @param page     the number of the page the items are on
	 * @param exchange the exchange that the stocks belong to
	 * @return {@link List} of {@link Stock}s that are on that exchange
	 */
	@GetMapping("/stock/{exchange}")
	public ResponseEntity<List<Stock>> getStocksByExchange(
			@RequestParam(value = "items", required = false, defaultValue = "10") int count,
			@RequestParam(value = "page", required = false, defaultValue = "10") int page,
			@PathVariable String exchange) {
		page = (page < 0) ? 0 : page;
		count = (count < 1) ? 1 : count;
		Pageable pagedContent = PageRequest.of(page, count);
		List<Stock> allStocksForExchange = stockService.getStocksByExchange(exchange, pagedContent);
		return new ResponseEntity<>(allStocksForExchange, HttpStatus.OK);
	}

	/**
	 * Add a stock by it's name (item is validated to ensure that such stock symbol
	 * exists for mentioned exchange) otherwise nothing is added and response code
	 * is: 1
	 * 
	 * @param stock The new {@link Stock} to add
	 * @return {@link List} of all {@link Stock}s
	 */
	@PostMapping("/stock")
	public ResponseEntity<Stock> addStock(@RequestBody Stock stock) {
		verifier.verifyNotNull(stock);
		verifier.verifyString(stock.getSymbol());
		verifier.verifyString(stock.getFullName());
		verifier.verifyString(stock.getExchange());
		Stock createdStock = stockService.createStock(stock.getSymbol(), stock.getFullName(), stock.getExchange());
		return new ResponseEntity<>(createdStock, HttpStatus.OK);
	}

	/**
	 * Delete an existing stock
	 * 
	 * @param stock The new {@link Stock} to delete
	 * @return {@link List} of all {@link Stock}s
	 */
	@DeleteMapping("/stock")
	public ResponseEntity<String> deleteStock(@RequestBody Stock stock) {
		verifier.verifyNotNull(stock);
		int deleteStatus = stockService.deleteStock(stock);
		if (deleteStatus == 0)
			return new ResponseEntity<>("Delete successful", HttpStatus.OK);
		return new ResponseEntity<>("Unknown error occured", HttpStatus.I_AM_A_TEAPOT);
	}

	/**
	 * Handle illegal argument exception
	 * 
	 * @return {@link ResponseEntity} with the error message
	 */
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<String> handleIllegalArgument() {
		return new ResponseEntity<>("Invalid argument provided", HttpStatus.I_AM_A_TEAPOT);
	}

	/**
	 * Handle general exception
	 * 
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleGeneralException() {
		return new ResponseEntity<>("Unknown error occured", HttpStatus.I_AM_A_TEAPOT);
	}
}
