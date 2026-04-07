package com.zcklab.api.dto;

import com.zcklab.api.Enums.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;



@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NinjaRequestDTO {

    @NotBlank(message = "Name is Required")
    @Size(min = 3, max = 12, message="Name min characters is 3 and max 12")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid Email Format")
    private String email;

    @NotNull(message = "Birth Date is Required")
    @Past(message = "Birth Date must be in the past")
    private LocalDate birthDate;

    @NotNull(message = "Category is Required")
    private Category category;

    @NotNull(message = "Ability is Required")
    private Ability ability;

    @NotNull(message = "Elemental is Required")
    private Elementals elementals;

    @NotNull(message = "Rank is Required")
    private Rank rank;



    @NotBlank(message = "Description is Required")
    @Size(max = 50, message = "Description should have a max of 50 characters" )
    private String description;
}
