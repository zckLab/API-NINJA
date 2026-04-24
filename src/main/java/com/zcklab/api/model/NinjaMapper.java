package com.zcklab.api.model;

import com.zcklab.api.dto.MissionsResponseDTO;
import com.zcklab.api.dto.NearbyVillageDTO;
import com.zcklab.api.dto.NinjaRequestDTO;
import com.zcklab.api.dto.NinjaResponseDTO;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface NinjaMapper {

    NinjaResponseDTO toResponseNinjaDTO(Ninja ninja);

    Ninja toEntity(NinjaRequestDTO dto);

    MissionsResponseDTO toMissionDTO(Missions missions);

    NearbyVillageDTO toNearbyDTO(NearbyVillages nearbyVillages);
}
