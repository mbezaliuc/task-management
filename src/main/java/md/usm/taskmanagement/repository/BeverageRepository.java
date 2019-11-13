package md.usm.taskmanagement.repository;

import md.usm.taskmanagement.model.Beverage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public interface BeverageRepository extends JpaRepository<Beverage, Long> {

    Optional<Beverage> findByName(String name);

    Optional<Beverage> findByAlcoholPercent(double alcoholPercent);
}

