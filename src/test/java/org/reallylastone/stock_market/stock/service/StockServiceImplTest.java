package org.reallylastone.stock_market.stock.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.reallylastone.stock_market.stock.domain.entity.Stock;
import org.reallylastone.stock_market.stock.domain.dto.StockDto;
import org.reallylastone.stock_market.stock.domain.repository.StockRepository;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StockServiceImplTest {

    @Mock
    private StockRepository stockRepository;

    @Mock
    private StockMapper stockMapper;

    @Test
    void listDelegatesToRepositoryAndMapper() {
        StockServiceImpl stockService = new StockServiceImpl(stockRepository, stockMapper);
        List<Stock> stocks = List.of(new Stock(1L, "ACME", "Acme Corp", BigDecimal.TEN));
        List<StockDto> dtos = List.of(new StockDto(1L, "ACME", "Acme Corp", BigDecimal.TEN));
        when(stockRepository.findAll()).thenReturn(stocks);
        when(stockMapper.toDtos(stocks)).thenReturn(dtos);

        assertEquals(dtos, stockService.list());
    }

    @Test
    void deleteDelegatesToRepository() {
        StockServiceImpl stockService = new StockServiceImpl(stockRepository, stockMapper);

        stockService.delete(1L);

        verify(stockRepository).deleteById(1L);
    }
}
