package com.isa.bloodtransfusion.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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

    @Column(name = "jmbg")
    private String jmbg;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "phone")
    private String phone;

    @Column(name = "gender")
    private Gender gender;

    @Column(name = "proffesion")
    private String proffesion;

    @Column(name = "proffesion_info")
    private String professionInfo;

    @ManyToMany(
            mappedBy = "admins"
    )
    private List<Center> centers = new ArrayList<>();

    @OneToOne(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "questionnaire_id",
            referencedColumnName = "user_id"
    )
    private Questionnaire questionnaire;

    public void addCenter(Center center) {
        centers.add(center);
        center.getAdmins().add(this);
    }

    @OneToMany(
            mappedBy = "user",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true
    )
    @JsonIgnore
    private List<Appointment> appointments = new ArrayList<>();

    @ManyToMany(
            mappedBy = "cancellations"
    )
    private List<Appointment> cancellations = new ArrayList<>();

    public void addToCancellationHistory(Appointment appointment) {
        cancellations.add(appointment);
        appointment.getCancellations().add(this);
    }
}
