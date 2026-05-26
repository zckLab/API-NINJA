package com.zcklab.api.dto;

import com.zcklab.api.enums.Category;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record NinjaResponseDTO(
        Long id,
        String name,
        String email,
        int age,
        Category category,
        List<MissionsDTO> missions,
        List<ClanDTO> clans
){}
