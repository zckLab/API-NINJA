package com.zcklab.api.dto;

import com.zcklab.api.enums.Category;
import jakarta.validation.constraints.*;

import java.util.List;

public record NinjaRequestDTO(


        @NotBlank(message = "Name is Required")
        @Size(min = 3, max = 50, message= "Name min characters is 3 and max 50")
        String username,

        @NotBlank
        @Size(min = 8, max = 100, message = "Password need to be min 8 characters and max 100")
        String password,

        @NotBlank(message = "Email is required")
        @Email(message = "Invalid Email Format")
        String email,

        @NotNull
        @Min(value = 0, message = "Age need to be positive")
        Integer age,

        @NotNull(message = "Category is Required")
        Category category,

        // For lists we use @NotEmpty (if is Required)
        @NotEmpty(message = "Missions are Required")
        List<Long> missionId,

        @NotNull(message = "Clan is Required")
        Long clanId
){}
