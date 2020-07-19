package com.alwaysup.stock.stockApi.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.alwaysup.stock.stockApi.model.Stock;

@Repository
public interface StockRepository extends CrudRepository<Stock, Integer> {

	/**
	 * Get All stocks
	 * 
	 * @param pageable
	 * @return {@link List} of {@link Stock}s that are active by requested page
	 */
	@Query("Select stock from Stock stock where stock.active=1 order by id")
	public List<Stock> getAll(Pageable pageable);

	/**
	 * Get All Stocks by the exchange name
	 * 
	 * @param exchange
	 * @param pageable
	 * @return {@link List} of {@link Stock}s that are active by requested page and
	 *         exchange
	 */
	@Query("Select stock from Stock stock where stock.exchange=?1 and stock.active=1 order by id")
	public List<Stock> getAllByExchange(String exchange, Pageable pageable);

	/**
	 * Get Stock by it's ID
	 * 
	 * @param id
	 * @return {@link Stock} that matches the id provided
	 */
	@Query("Select stock from Stock stock where stock.id=?1 and stock.active=1")
	public Stock getById(int id);

	/**
	 * Get Stock by it's symbol and exchange
	 * 
	 * @param symbol
	 * @param exchange
	 * @return {@link Stock} that matches the symbol and exchange provided
	 */
	@Query("Select stock from Stock stock where stock.symbol=?1 and stock.exchange=?2 and stock.active=1")
	public Stock getBySymbolAndExchange(String symbol, String exchange);

	/**
	 * Get Stock by it's symbol
	 * 
	 * @param symbol
	 * @param exchange
	 * @return {@link Stock} that matches the symbol provided
	 */
	@Query("Select stock from Stock stock where stock.symbol=?1 and stock.active=1")
	public Stock getBySymbol(String symbol);
}
