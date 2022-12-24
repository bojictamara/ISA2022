package com.isa.bloodtransfusion.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "centers")
public class Center {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "address_id",
            referencedColumnName = "id"
    )
    private Address address;
    @Column(name = "description")
    private String description;
    @Column(name = "average_rate")
    private double averageRate;

    @OneToMany(
            mappedBy = "center",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true
    )
    private List<Appointment> appointments = new ArrayList<>();

    @ManyToMany(
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE}
    )
    @JoinTable(
            name = "center_admins",
            joinColumns = @JoinColumn(
                    name = "center_id",
                    foreignKey = @ForeignKey(
                            name = "center_admins__center_id"
                    )
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "user_id",
                    foreignKey = @ForeignKey(
                            name = "center_admins__user_id"
                    )
            )
    )
    private List<User> admins = new ArrayList<>();

    public void addFreeAppointment(Appointment appointment) {
        if (!appointments.contains(appointment)) {
            appointments.add(appointment);
            appointment.setCenter(this);
        }
    }

    public void addAdmin(User admin) {
        admins.add(admin);
        admin.getCenters().add(this);
    }

}
