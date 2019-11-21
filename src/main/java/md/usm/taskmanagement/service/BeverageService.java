package md.usm.taskmanagement.service;

import md.usm.taskmanagement.model.Beverage;
import md.usm.taskmanagement.repository.BeverageRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BeverageService {

    private BeverageRepository beverageRepository;

    public BeverageService(BeverageRepository beverageRepository) {
        this.beverageRepository = beverageRepository;
    }

    public List<Beverage> getAllowedDrinks() {
        return beverageRepository.findAll()
                .stream()
                .filter(beverage -> !beverage.isAlcohol())
                .filter(beverage -> beverage.getPrice().compareTo(BigDecimal.valueOf(50)) < 0)
                .collect(Collectors.toList());

    }

    public List<Beverage> getDrinks() {
        return beverageRepository.findAll();
    }

    public boolean isWorkingTime() {
        LocalDateTime localDateTime = LocalDateTime.now();
        boolean isWorkingDay = !(localDateTime.getDayOfWeek() == DayOfWeek.SUNDAY || localDateTime.getDayOfWeek() == DayOfWeek.SATURDAY);

        return isWorkingDay && (localDateTime.getHour() < 18 && localDateTime.getHour() > 8);
    }

    public void init() {
        beverageRepository.saveAll(Arrays.asList(new Beverage("water", BigDecimal.TEN, 0.00, "H2O"),
                new Beverage("Beer", BigDecimal.valueOf(15.00), 4.5, "Magical HOH"))
        );
    }
}
