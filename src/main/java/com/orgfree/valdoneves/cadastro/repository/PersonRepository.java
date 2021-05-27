package com.orgfree.valdoneves.cadastro.repository;

import com.orgfree.valdoneves.cadastro.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {


}
