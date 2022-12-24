package com.isa.bloodtransfusion.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "lastname")
    private String lastName;
    @Column(name = "username", unique = true)
    private String username;
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "role")
    @Enumerated(value = EnumType.STRING)
    private ERole role;
    @Column(name = "password")
    private String password;
    @Column(name = "account_verified")
    private boolean accountVerified;
    @ManyToMany(
            mappedBy = "admins"
    )
    private List<Center> centers = new ArrayList<>();

    public void addCenter(Center center) {
        centers.add(center);
        center.getAdmins().add(this);
    }

    @OneToMany(
            mappedBy = "user",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true
    )
    private List<Appointment> appointments = new ArrayList<>();
}
