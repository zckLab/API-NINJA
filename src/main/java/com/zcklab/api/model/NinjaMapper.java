package com.zcklab.api.model;

import com.zcklab.api.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface NinjaMapper {

    NinjaResponseDTO toResponseNinjaDTO(Ninja ninja);
    Ninja toEntity(NinjaRequestDTO dto);



    @Mapping(source = "name", target = "MissionName")
    @Mapping(source = "description", target = "MissionDescription")
    MissionsDTO toMissionDTO(Missions missions);

    @Mapping(source = "name", target = "VillageName")
    NearbyVillageDTO toNearbyDTO(NearbyVillages nearbyVillages);


    ClanDTO toClanDTO(Clan clan);
}
