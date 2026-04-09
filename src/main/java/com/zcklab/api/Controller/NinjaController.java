package com.zcklab.api.Controller;



import com.zcklab.api.Service.ServiceNinja;
import com.zcklab.api.dto.NinjaRequestDTO;
import com.zcklab.api.dto.NinjaResponseDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ninjas")
@AllArgsConstructor
public class NinjaController {

    private final ServiceNinja serviceNinja;


    // ResponseEntity is used to display HTTP protocols correctly.
    // Here are the 4 methods for a REST API.





    // Here, we use the findAllNinjas method from the Service.
    // It gets all entities, transforms each one using the toResponseDTO method (to safely convert the model into a response),
    // and then returns everything as a list.
    @GetMapping("/listninjas")
    public ResponseEntity<List<NinjaResponseDTO>> getAllNinjas(){
        return ResponseEntity.ok(serviceNinja.findAllNinjas()); // Return 200
    }





    // To filter by name, we first create the endpoint "/getname" and it will return a Response that uses @RequestParam,
    // which serves to extract values that come in the URL.
    // You know when you create an account, for example, and then click on "show name"
    // and a URL like "/users/search?name=Gabriel" appears? What comes after the "?"
    // are the request parameters that were retrieved and delivered to the variable in the project.
    @GetMapping("/getname")
    public ResponseEntity<NinjaResponseDTO> searchByName(@RequestParam String name){
        return ResponseEntity.ok(serviceNinja.findByName(name));

        // So, we call the request name parameter and return the HTTP status ok (200)
        // when serviceNinja is able to find the name.
    }






    @GetMapping("/getemail")
    public ResponseEntity<NinjaResponseDTO> searchByEmail(@RequestParam String email){
        return ResponseEntity.ok(serviceNinja.findByEmail(email));
    }






    // Without the @Valid attribute, the request would be accepted without meeting the RequestDTO requirements.
    //We created a ResponseDTO that calls the createNinja method.
    // It receives the Request, uses toEntity to convert it to Entity, and then toResponse to transform it into ResponseDTO,
    // returning the result (This may seem confusing at first glance)
    @PostMapping("/registerninja")
    public ResponseEntity<NinjaResponseDTO> createNinja(@RequestBody @Valid NinjaRequestDTO ninjaDTO){
        NinjaResponseDTO newNinja = serviceNinja.createNinja(ninjaDTO);
        return new ResponseEntity<>(newNinja, HttpStatus.CREATED); // Return 201
    }






    //Actually, this is the easiest one.
    //We just use a Void method (return nothing) and call deleteNinja
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNinja(@PathVariable Long id){
        serviceNinja.deleteNinja(id);
        return ResponseEntity.noContent().build(); //Return 204 No Content (without this line,
                                                  // that would return 200 (not the correct one))
    }






    //We create the method updateNinja and use the both (@PathVariable and @RequestBody).
    //Retrun the status ok (200) and inside it the variable calls the updateNinja method with its respective values.
    @PutMapping("/{id}")
    public ResponseEntity<NinjaResponseDTO> updateNinja(@PathVariable Long id, @RequestBody @Valid NinjaRequestDTO ninjaRequestDTO){
        return ResponseEntity.ok(serviceNinja.updateNinja(id, ninjaRequestDTO)); // Return 200
    }

}
