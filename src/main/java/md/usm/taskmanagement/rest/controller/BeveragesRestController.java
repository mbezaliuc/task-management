package md.usm.taskmanagement.rest.controller;


import lombok.RequiredArgsConstructor;
import md.usm.taskmanagement.model.Beverage;
import md.usm.taskmanagement.rest.dto.BeverageDTO;
import md.usm.taskmanagement.service.BeverageService;
import org.hibernate.exception.ConstraintViolationException;
import org.postgresql.util.PSQLException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/rest")
@RequiredArgsConstructor
public class BeveragesRestController {

    private final BeverageService beverageService;

    @RequestMapping(value = "/random")
    public BeverageDTO showRandomBeverage() {
        return new BeverageDTO();
    }

    @RequestMapping(value = "/alldrinks")
    public List<BeverageDTO> showAllBeverages() {
        return beverageService.getDrinks().stream()
                .map(b -> new BeverageDTO(b.getName(), b.getAlcoholPercent(), b.getDescription()))
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String saveBeverage(@RequestBody BeverageDTO beverageDTO) {
        String response = "Success";
        try {
            beverageService.save(new Beverage(beverageDTO.getName(), BigDecimal.ZERO, beverageDTO.getAlcoholPercent(), beverageDTO.getDescription()));
        } catch (DataIntegrityViolationException ex) {
            response = "Fail to save, due to duplicate name.";
        }
        return response;
    }

    @DeleteMapping(value = "/remove/{name}")
    public void removeBeverage(@PathVariable String name) {
        beverageService.deleteByName(name);
    }

    @PutMapping(value = "update/{id}")
        public void updateBeverage(@PathVariable int id, @RequestBody BeverageDTO beverageDTO){
        beverageService.update(id, beverageDTO);
    }

}
