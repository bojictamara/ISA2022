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
