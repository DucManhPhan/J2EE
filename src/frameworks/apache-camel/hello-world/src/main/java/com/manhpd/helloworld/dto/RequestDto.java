package com.manhpd.helloworld.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestDto {

    @NotEmpty(message = "Enter valid id")
    @Pattern(regexp = "^[0-9]*$", message = "id must be in number")
    @Min(value = 1)
    @JsonProperty("id")
    private String id;

    @NotEmpty(message = "Enter the valid name")
    @JsonProperty("name")
    private String name;

}
