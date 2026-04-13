package com.zcklab.api.dto;

import com.zcklab.api.Enums.Ability;
import com.zcklab.api.Enums.Category;
import com.zcklab.api.Enums.Elementals;
import com.zcklab.api.Enums.Rank;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record NinjaRequestDTO(


        @NotBlank(message = "Name is Required")
        @Size(min = 3, max = 50, message="Name min characters is 3 and max 50")
        String name,

        @NotBlank
        @CPF(message = "CPF is Required") //This is a brazilian validation for the CPF(Brazilian tax identification number)
        String cpf,

        @NotBlank(message = "Email is required")
        @Email(message = "Invalid Email Format")
        String email,

        @NotNull(message = "Birth Date is Required")
        @Past(message = "Birth Date must be in the past") // makes the date not today or in the future (correct use for past or today @PastOrPresent)
        LocalDate birthDate,

        @NotNull(message = "Category is Required")
        Category category,

        @NotNull(message = "Ability is Required")
        Ability ability,

        @NotNull(message = "Elemental is Required")
        Elementals elementals,

        @NotNull(message = "Rank is Required")
        Rank rank,

        @NotBlank(message = "Description is Required")
        @Size(max = 120, message = "Description should have a max of 120 characters" )
        String description

){}
