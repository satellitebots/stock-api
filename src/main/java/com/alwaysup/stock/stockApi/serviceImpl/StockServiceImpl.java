package com.alwaysup.stock.stockApi.serviceImpl;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import com.alwaysup.stock.stockApi.model.Stock;
import com.alwaysup.stock.stockApi.repository.StockRepository;
import com.alwaysup.stock.stockApi.service.StockService;
import com.alwaysup.stock.stockApi.util.Verifier;

public class StockServiceImpl implements StockService {
	@Autowired
	private StockRepository stockRepository;

	@Autowired
	private Verifier verifier;

	/**
	 * @inheritDoc
	 */
	@Override
	public Stock createStock(String symbol, String fullName, String exchange) throws IllegalArgumentException {
		verifier.verifyString(symbol);
		verifier.verifyString(fullName);
		verifier.verifyString(exchange);
		Stock tempStock = stockRepository.getBySymbol(symbol);
		if (tempStock != null && tempStock.getSymbol().equals(symbol)) {
			return null;
		}
		Stock newStock = new Stock(symbol, fullName, exchange);
		try {
			String urlStr = String.format("http://d.yimg.com/autoc.finance.yahoo.com/autoc?query=%s&region=1&lang=en",
					symbol);
			URL url = new URL(urlStr);
			JSONTokener tokener = new JSONTokener(url.openStream());
			JSONObject jsonResponse = new JSONObject(tokener);
			JSONObject data = jsonResponse.getJSONObject("ResultSet").getJSONArray("Result").getJSONObject(0);
			String respSymbol = data.getString("symbol");
			String respExch = data.getString("exch");
			String respExchange = data.getString("exchDisp");
			String respName = data.getString("name");
			if (symbol.toUpperCase().equals(respSymbol)
					&& (exchange.toUpperCase().equals(respExch) || exchange.toUpperCase().equals(respExchange))) {
				newStock.setExchange(respExchange);
				newStock.setFullName(respName);
			} else {
				// return null if the stock is invalid
				return null;
			}
		} catch (IOException ioerr) {
			// return null if there are any issues
			return null;
		}
		stockRepository.save(newStock);
		return newStock;
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public Stock getStockById(int id) {
		Stock stock = stockRepository.getById(id);
		return stock;
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public int deleteStock(Stock stock) {
		verifier.verifyNotNull(stock);
		verifier.verifyString(stock.getSymbol());
		verifier.verifyString(stock.getExchange());
		try {
			Stock stockToDelete = stockRepository.getBySymbol(stock.getSymbol());
			stockToDelete.setActive(0);
			stockRepository.save(stockToDelete);
		} catch (Exception e) {
			return 1;
		}
		return 0;
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public List<Stock> getStocksByExchange(String exchange, Pageable pageable) throws IllegalArgumentException {
		verifier.verifyNotNull(pageable);
		verifier.verifyString(exchange);
		return stockRepository.getAllByExchange(exchange, pageable);
	}

	@Override
	public List<Stock> getStocks(Pageable pageable) throws IllegalArgumentException {
		verifier.verifyNotNull(pageable);
		return stockRepository.getAll(pageable);
	}
}