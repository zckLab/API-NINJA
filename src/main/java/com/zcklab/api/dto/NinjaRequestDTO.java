package com.zcklab.api.dto;

import com.zcklab.api.Enums.Ability;
import com.zcklab.api.Enums.Category;
import com.zcklab.api.Enums.Elementals;
import com.zcklab.api.Enums.Rank;
import com.zcklab.api.Model.Ninja;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record NinjaRequestDTO(
        @NotBlank(message = "Name is Required")
        @Size(min = 3, max = 20, message="Name min characters is 3 and max 12")
        String name,

        @NotBlank(message = "Email is required")
        @Email(message = "Invalid Email Format")
        String email,

        @NotNull(message = "Birth Date is Required")
        @Past(message = "Birth Date must be in the past")
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
        @Size(max = 50, message = "Description should have a max of 50 characters" )
        String description

){}
