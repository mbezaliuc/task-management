package md.usm.taskmanagement.service;

import md.usm.taskmanagement.model.Beverage;
import md.usm.taskmanagement.repository.BeverageRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BeverageServiceTest {

    @Mock
    private BeverageRepository beverageRepository;

    @InjectMocks
    private BeverageService beverageService;

    @AfterEach
    public void after() {
        verifyNoMoreInteractions(beverageRepository);
    }

    @Test
    public void getAllowedDrinksWhenNoDrinksFoundTest() {
        when(beverageRepository.findAll()).thenReturn(new ArrayList<>());

        List<Beverage> resultList = beverageService.getAllowedDrinks();

        assertTrue(resultList.isEmpty());
    }

    @Test
    public void getAllowedDrinksWhenAllAlcoholTest() {
        List<Beverage> beverages = asList(
                new Beverage("Fire Water", BigDecimal.valueOf(200), 40.0, "Drink 4 real mates"),
                new Beverage("Children water", BigDecimal.valueOf(100), 6.8, "Beer"),
                new Beverage("Long Island Ice Tea", BigDecimal.valueOf(201), 25.0, "New Lipton stuff")

        );
        when(beverageRepository.findAll()).thenReturn(beverages);

        List<Beverage> resultList = beverageService.getAllowedDrinks();

        assertTrue(resultList.isEmpty());
    }

    @Test
    public void getAllowedDrinksWhenNoneAlcoholAndPriceGreaterThen50Test() {
        List<Beverage> beverages = asList(
                new Beverage("Fire Water", BigDecimal.valueOf(100), 0, "Really hot watter"),
                new Beverage("Water", BigDecimal.valueOf(100), 0, "Not Beer"),
                new Beverage("Lipton", BigDecimal.valueOf(201), 0, "New Lipton stuff")

        );
        when(beverageRepository.findAll()).thenReturn(beverages);

        List<Beverage> resultList = beverageService.getAllowedDrinks();

        assertTrue(resultList.isEmpty());

        verify(beverageRepository).findAll();
    }

    @Test
    public void getAllowedDrinksTest() {
        List<Beverage> beverages = asList(
                new Beverage("Fire Water", BigDecimal.valueOf(100), 0, "Really hot watter"),
                new Beverage("Water", BigDecimal.valueOf(100), 0, "Not Beer"),
                new Beverage("Lipton", BigDecimal.valueOf(201), 0, "New Lipton stuff"),
                new Beverage("Fire Water", BigDecimal.valueOf(200), 40.0, "Drink 4 real mates"),
                new Beverage("Children water", BigDecimal.valueOf(100), 6.8, "Beer"),
                new Beverage("Long Island Ice Tea", BigDecimal.valueOf(201), 25.0, "New Lipton stuff")
        );
        when(beverageRepository.findAll()).thenReturn(beverages);
        List<Beverage> resultList = beverageService.getAllowedDrinks();

        assertThat(resultList.size()).isEqualTo(3);
        assertThat(resultList).containsExactlyInAnyOrder(beverages.get(0), beverages.get(1), beverages.get(2));
    }
}