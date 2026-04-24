package com.zcklab.api.service;

import com.zcklab.api.dto.MissionsResponseDTO;
import com.zcklab.api.dto.NearbyVillageDTO;
import com.zcklab.api.model.Missions;
import com.zcklab.api.model.NearbyVillages;
import com.zcklab.api.model.Ninja;
import com.zcklab.api.model.NinjaMapper;
import com.zcklab.api.repository.RepositoryNinja;
import com.zcklab.api.dto.NinjaRequestDTO;
import com.zcklab.api.dto.NinjaResponseDTO;
import com.zcklab.api.handler.NinjaNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ServiceNinja {

    private final RepositoryNinja repositoryNinja;
    private final NinjaMapper ninjaMapper;


    // Anything that goes to a method that returns something will always be a Response,
    // and whenever it's a creation method, we'll transform the Request into an Entity
    // (for updating, we'll use both: transform it into an entity, save it, and update it back to a Request).






    // 1. Find All
    public List<NinjaResponseDTO> findAllNinjas() {
        List<Ninja> ninjas = repositoryNinja.findAll();

        if (ninjas.isEmpty()) {
            throw new NinjaNotFoundException("Ninja not found");
        }

                return ninjas.stream()
                .map(ninjaMapper::toResponseNinjaDTO)
                .collect(Collectors.toList());
    }






    // 2. Create
    public NinjaResponseDTO createNinja(NinjaRequestDTO ninjaDTO) {

        Ninja ninjaConverted = toEntity(ninjaDTO);

        Ninja ninjaSaved = repositoryNinja.save(ninjaConverted);

        return ninjaMapper.toResponseNinjaDTO(ninjaSaved);
    }






    // 3. Delete

    // A conditional statement that, if it does not exist, returns a RuntimeException.
    public void deleteNinja(Long id) {
        if (!repositoryNinja.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ninja not found");
        }
        repositoryNinja.deleteById(id);
    }






    // 4. Update
    public NinjaResponseDTO updateNinja(Long id, NinjaRequestDTO ninjaDTO) {

        Ninja ninjaExisting = repositoryNinja.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ninja not found"));

        ninjaExisting.setName(ninjaDTO.name());
        ninjaExisting.setCpf(ninjaDTO.cpf());
        ninjaExisting.setEmail(ninjaDTO.email());
        ninjaExisting.setBirthDate(ninjaDTO.birthDate());
        ninjaExisting.setCategory(ninjaDTO.category());
        ninjaExisting.setAbility(ninjaDTO.ability());
        ninjaExisting.setElementals(ninjaDTO.elementals());
        ninjaExisting.setRank(ninjaDTO.rank());
        ninjaExisting.setDescription(ninjaDTO.description());

        return ninjaMapper.toResponseNinjaDTO(repositoryNinja.save(ninjaExisting));
    }






    // 5. Search by name

    // Here, we create the `findByName` method that retrieves the String "name",
    // and create an entity called "nameNinja" that will call the existing `findByName(name)`
    // method in the repository. We throw a `RuntimeException` if the value is null.
    public NinjaResponseDTO findByName(String name) {
        Ninja nameNinja = repositoryNinja.findByName(name)
                .orElseThrow(() -> new NinjaNotFoundException("Ninja not found"));

        return ninjaMapper.toResponseNinjaDTO(nameNinja); // If everything is OK, we return the `toResponse` method to `nameNinja`.
    }





    public NinjaResponseDTO findByEmail(String email) {
        Ninja emailNinja = repositoryNinja.findByEmail(email).orElseThrow(() -> new NinjaNotFoundException("Ninja not found"));

        return ninjaMapper.toResponseNinjaDTO(emailNinja);
    }






    // Mapper: RequestDTO -> Entity
    private Ninja toEntity(NinjaRequestDTO dto) {
        Ninja ninja = new Ninja();

        ninja.setName(dto.name());
        ninja.setCpf(dto.cpf());
        ninja.setEmail(dto.email());
        ninja.setBirthDate(dto.birthDate());
        ninja.setCategory(dto.category());
        ninja.setAbility(dto.ability());
        ninja.setElementals(dto.elementals());
        ninja.setRank(dto.rank());
        ninja.setDescription(dto.description());

        return ninja;
    }
}