package com.orgfree.valdoneves.cadastro.controller;

import com.orgfree.valdoneves.cadastro.dto.request.PersonDTO;
import com.orgfree.valdoneves.cadastro.dto.response.MessageResponseDTO;
import com.orgfree.valdoneves.cadastro.entity.Person;
import com.orgfree.valdoneves.cadastro.exception.PersonNotFoundException;
import com.orgfree.valdoneves.cadastro.repository.PersonRepository;
import com.orgfree.valdoneves.cadastro.service.PersonService;
import lombok.AllArgsConstructor;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/people")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonController {

    private PersonService personService;

//    a anotação na linha 21 representa esse contrutor padrão
//    @Autowired
//    public PersonController(PersonService personService){
//        this.personService = personService;
//    }

    //criação de um livro
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createPerson(@RequestBody @Valid PersonDTO personDTO){
        return personService.createPerson(personDTO);
    }

    @GetMapping
    public List<PersonDTO> listAll(){
        return personService.listAll();
    }

    @GetMapping("/{id}")
    public PersonDTO findById(@PathVariable Long id) throws PersonNotFoundException {
        return personService.findById(id);
    }

    @PutMapping("/{id}")
    public MessageResponseDTO updateById(@PathVariable Long id, @RequestBody PersonDTO personDTO) throws PersonNotFoundException {
        return personService.updateById(id, personDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws PersonNotFoundException {
        personService.delete(id);
    }

}
