package com.orgfree.valdoneves.cadastro.service;

import com.orgfree.valdoneves.cadastro.dto.request.PersonDTO;
import com.orgfree.valdoneves.cadastro.dto.response.MessageResponseDTO;
import com.orgfree.valdoneves.cadastro.entity.Person;
import com.orgfree.valdoneves.cadastro.exception.PersonNotFoundException;
import com.orgfree.valdoneves.cadastro.mapper.PersonMapper;
import com.orgfree.valdoneves.cadastro.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/*
* Classe responsavel por implementar todas as regras e negocio
* */


@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonService {
    private PersonRepository personRepository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;
    //    a anotação na linha 24 representa esse contrutor padrão
//    @Autowired
//    public PersonService(PersonRepository personRepository){
//        this.personRepository = personRepository;
//    }

    public MessageResponseDTO createPerson(PersonDTO personDTO){
        Person personToSave = personMapper.toModel(personDTO);
        Person savedPerson = personRepository.save(personToSave);
        return createMessageResponse(savedPerson.getId(), "Create person with ID: ");
    }

    public List<PersonDTO> listAll() {
        List<Person> allPeople = personRepository.findAll();
        return allPeople.stream().map(personMapper::toDTO).collect(Collectors.toList());
    }


    public PersonDTO findById(Long id) throws PersonNotFoundException {
        //Optional evita verificações como null

//        Optional<Person> opcionalPerson = personRepository.findById(id);
//        if(opcionalPerson.isEmpty()){
//            throw  new PersonNotFoundException(id);
//        }
//        return personMapper.toDTO(opcionalPerson.get());

        //utilizando lambidas, assim não precisamos passar pelo optional
//        Person person = personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));
//        linhas estraida para um metodo
        Person person = verifyIfExists(id);
        return personMapper.toDTO(person);

    }
    public MessageResponseDTO updateById(Long id, PersonDTO personDTO) throws PersonNotFoundException {
        verifyIfExists(id);
        Person personToUpdate = personMapper.toModel(personDTO);
        Person updatePerson = personRepository.save(personToUpdate);
        return createMessageResponse(updatePerson.getId(), "Update person with ID: ");
    }

    public void delete(Long id) throws PersonNotFoundException {
//        personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));
        verifyIfExists(id);
        personRepository.deleteById(id);
    }

    private Person verifyIfExists(Long id) throws PersonNotFoundException{
        return personRepository.findById(id).orElseThrow( () -> new PersonNotFoundException(id));
    }


    private MessageResponseDTO createMessageResponse(Long id, String message) {
        return MessageResponseDTO
                .builder()
                .message(message + id)
                .build();
    }
}
