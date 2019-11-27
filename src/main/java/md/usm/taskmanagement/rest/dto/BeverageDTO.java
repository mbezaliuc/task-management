package md.usm.taskmanagement.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BeverageDTO {

    @JsonProperty("beverage-name")
    private String name;

    private double alcoholPercent;

    private String description;
}
