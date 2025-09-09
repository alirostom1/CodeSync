package com.codesync.backend.model.entity;

import com.codesync.backend.model.enums.ERole;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@Table(name = "roles")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter 

public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name ;

}
