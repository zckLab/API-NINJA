package com.zcklab.api.service;

import com.zcklab.api.dto.ClanCreateDTO;
import com.zcklab.api.dto.ClanDTO;
import com.zcklab.api.model.Clan;
import com.zcklab.api.model.Ninja;
import com.zcklab.api.model.NinjaMapper;
import com.zcklab.api.repository.ClanRepository;
import com.zcklab.api.repository.RepositoryNinja;
import com.zcklab.api.dto.NinjaRequestDTO;
import com.zcklab.api.dto.NinjaResponseDTO;
import com.zcklab.api.handler.NinjaNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class ServiceNinja {

    private final RepositoryNinja repositoryNinja;
    private final NinjaMapper ninjaMapper;
    private final ClanRepository repositoryClan;


    // Anything that goes to a method that returns something will always be a Response,
    // and whenever it's a creation method, we'll transform the Request into an Entity
    // (for updating, we'll use both: transform it into an entity, save it, and update it back to a Request).






    // 1. Find All
    public Page<NinjaResponseDTO> findAllNinjas(int page, int items) {

        Pageable pageable = PageRequest.of(
                page,
                items,
                Sort.by("name").ascending()
                        .and(Sort.by("email").ascending())
        );

        Page<Ninja> ninjasPage = repositoryNinja.findAll(pageable);

        return ninjasPage.map(ninjaMapper::toResponseNinjaDTO);
    }






    // 2. Create
    public NinjaResponseDTO createNinja(NinjaRequestDTO ninjaDTO) {

        Ninja ninjaConverted = ninjaMapper.toEntity(ninjaDTO);

        Ninja ninjaSaved = repositoryNinja.save(ninjaConverted);

        return ninjaMapper.toResponseNinjaDTO(ninjaSaved);
    }


    public ClanDTO createClan(ClanCreateDTO dto) {

        List<Ninja> ninjas = repositoryNinja.findAllById(dto.ninjaIds());

        Clan clan = new Clan();
        clan.setName(dto.name());
        clan.setPopulation(dto.population());
        clan.setNinja_clans(ninjas);

        Clan savedClan = repositoryClan.save(clan);

        return new ClanDTO(
                savedClan.getId(),
                savedClan.getName(),
                savedClan.getPopulation()
        );
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
        ninjaExisting.setAge(ninjaDTO.age());
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




    // 6. Search by email
    public NinjaResponseDTO findByEmail(String email) {
        Ninja emailNinja = repositoryNinja.findByEmail(email).orElseThrow(() -> new NinjaNotFoundException("Ninja not found"));

        return ninjaMapper.toResponseNinjaDTO(emailNinja);
    }

    // 7. Find All Emails
    public List<String> findAllMails() {

        return repositoryNinja.findAllEmails();
    }




}