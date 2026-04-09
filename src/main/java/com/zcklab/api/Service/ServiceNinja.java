package com.zcklab.api.Service;

import com.zcklab.api.Model.Ninja;
import com.zcklab.api.Repository.RepositoryNinja;
import com.zcklab.api.dto.NinjaRequestDTO;
import com.zcklab.api.dto.NinjaResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ServiceNinja {

    private final RepositoryNinja repositoryNinja;

    // 1. Find All
    public List<NinjaResponseDTO> findAllNinjas() {
        return repositoryNinja.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    // 2. Create
    public NinjaResponseDTO createNinja(NinjaRequestDTO ninjaDTO) {

        Ninja ninjaConverted = toEntity(ninjaDTO);

        Ninja ninjaSaved = repositoryNinja.save(ninjaConverted);

        return toResponseDTO(ninjaSaved);
    }

    // 3. Delete

    // A conditional statement that, if it does not exist, returns a RuntimeException.
    public void deleteNinja(Long id) {
        if (!repositoryNinja.existsById(id)) {
            throw new RuntimeException("Ninja not found");
        }
        repositoryNinja.deleteById(id);
    }

    // 4. Update
    public NinjaResponseDTO updateNinja(Long id, NinjaRequestDTO ninjaDTO) {

        Ninja ninjaExisting = repositoryNinja.findById(id)
                .orElseThrow(() -> new RuntimeException("Ninja not found"));

        ninjaExisting.setName(ninjaDTO.name());
        ninjaExisting.setEmail(ninjaDTO.email());
        ninjaExisting.setBirthDate(ninjaDTO.birthDate());
        ninjaExisting.setCategory(ninjaDTO.category());
        ninjaExisting.setAbility(ninjaDTO.ability());
        ninjaExisting.setElementals(ninjaDTO.elementals());
        ninjaExisting.setRank(ninjaDTO.rank());
        ninjaExisting.setDescription(ninjaDTO.description());

        return toResponseDTO(repositoryNinja.save(ninjaExisting));
    }

    // Mapper: RequestDTO -> Entity
    private Ninja toEntity(NinjaRequestDTO dto) {
        Ninja ninja = new Ninja();

        ninja.setName(dto.name());
        ninja.setEmail(dto.email());
        ninja.setBirthDate(dto.birthDate());
        ninja.setCategory(dto.category());
        ninja.setAbility(dto.ability());
        ninja.setElementals(dto.elementals());
        ninja.setRank(dto.rank());
        ninja.setDescription(dto.description());

        return ninja;
    }

    // Mapper: Entity -> ResponseDTO
    private NinjaResponseDTO toResponseDTO(Ninja ninja) {
        return new NinjaResponseDTO(
                ninja.getId(),
                ninja.getName(),
                ninja.getEmail(),
                ninja.getBirthDate(),
                ninja.getCategory(),
                ninja.getAbility(),
                ninja.getElementals(),
                ninja.getRank(),
                ninja.getDescription()

        );
    }
}