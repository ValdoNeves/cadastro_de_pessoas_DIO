package com.orgfree.valdoneves.cadastro.service;

import com.orgfree.valdoneves.cadastro.dto.request.PersonDTO;
import com.orgfree.valdoneves.cadastro.dto.response.MessageResponseDTO;
import com.orgfree.valdoneves.cadastro.entity.Person;
import com.orgfree.valdoneves.cadastro.repository.PersonRepository;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.orgfree.valdoneves.cadastro.utils.PersonUtils.createFakeDTO;
import static com.orgfree.valdoneves.cadastro.utils.PersonUtils.createFakeEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;

    @Test
    void testGivenPersonDTOThenReturnSavedMessage(){
        PersonDTO personFakeDTO = createFakeDTO();
        Person personFake = createFakeEntity();


        when(personRepository.save(any(Person.class))).thenReturn(personFake);

        MessageResponseDTO expectedSucessMessage = createExpectedMessageResponse(personFake.getId());
        MessageResponseDTO successMessage = personService.createPerson(personFakeDTO);

        assertEquals(expectedSucessMessage, successMessage);
    }

    private MessageResponseDTO createExpectedMessageResponse(Long id) {
        return MessageResponseDTO
                .builder()
                .message("Create person with ID: " + id)
                .build();
    }
}
