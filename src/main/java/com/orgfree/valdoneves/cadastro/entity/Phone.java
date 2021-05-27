package com.orgfree.valdoneves.cadastro.entity;

import com.orgfree.valdoneves.cadastro.enums.PhoneType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;

//database
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //id autoincremental
    private Long id;

    @Enumerated(EnumType.STRING) //pega os valores dentro de um enum
    @Column(nullable = false) // valor não pode ser nulo NOT NULL
    private PhoneType type;

    @Column(nullable = false)
    private String number;
}
