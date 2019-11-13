package md.usm.taskmanagement.model;

import com.sun.javafx.beans.IDProperty;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "T_BEVERAGE")

public class Beverage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PRICE")
    private BigDecimal price;

    @Column(name = "ALCOHOL_PERCENT")
    private double alcoholPercent;

    @Column(name = "DESCRIPTION")
    private String description;

    private Beverage() {
        //for hibernate
    }

    public Beverage(String name, BigDecimal price, double alcoholPercent, String description) {
        this.name = name;
        this.price = price;
        this.alcoholPercent = alcoholPercent;
        this.description = description;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public double getAlcoholPercent() {
        return alcoholPercent;
    }

    public void setAlcoholPercent(double alcoholPercent) {
        this.alcoholPercent = alcoholPercent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAlcohol() {
        return alcoholPercent > 0;
    }
}
