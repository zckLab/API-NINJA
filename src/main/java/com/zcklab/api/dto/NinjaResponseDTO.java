package com.zcklab.api.dto;

import com.zcklab.api.enums.Ability;
import com.zcklab.api.enums.Category;
import com.zcklab.api.enums.Elementals;
import com.zcklab.api.enums.Rank;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record NinjaResponseDTO(
        Long id,
        String name,
        int age,
        String cpf,
        String email,
        LocalDate birthDate,
        Category category,
        Ability ability,
        Elementals elementals,
        Rank rank,
        List<MissionsDTO> missions,
        NearbyVillageDTO nearbyVillages,
        List<ClanDTO> clans,
        String description,
        Boolean active
){}
