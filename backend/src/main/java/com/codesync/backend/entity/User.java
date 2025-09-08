package com.codesync.backend.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="users")
@Data
@NoArgsConstructor
@AllArgsConstructor

@Getter @Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    public User(String username,String email,String password){
        this.username = username;
        this.email = email;
        this.password = password;
    }
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="user_roles",
                joinColumns = @JoinColumn(name="user_id"),
                inverseJoinColumns = @JoinColumn(name="role_id"))
    public Set<Role> roles = new HashSet<>();
    
}
