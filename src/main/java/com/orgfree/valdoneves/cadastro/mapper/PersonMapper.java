package com.orgfree.valdoneves.cadastro.mapper;


import com.orgfree.valdoneves.cadastro.dto.request.PersonDTO;
import com.orgfree.valdoneves.cadastro.entity.Person;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;



@Mapper
public interface PersonMapper {

    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    //target é o Person
    @Mapping(target= "birthDate", source = "birthDate", dateFormat = "dd-MM-yyyy")
    Person toModel(PersonDTO personDTO);

    PersonDTO toDTO(Person person);
}
