package com.isa.bloodtransfusion.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "appointments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start")
    LocalDateTime start;

    @ManyToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "center_id",
            nullable = false
    )
    Center center;

    @ManyToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "medical_worker_id",
            nullable = true
    )
    User medicalWorker;

    @ManyToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "user_id",
            nullable = true
    )
    User user;

    @ManyToMany(
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE}
    )
    @JoinTable(
            name = "cancellation_history",
            joinColumns = @JoinColumn(
                    name = "appointment_id",
                    foreignKey = @ForeignKey(
                            name = "appointment_cancellations__appointment_id"
                    )
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "user_id",
                    foreignKey = @ForeignKey(
                            name = "appointment_cancellations__user_id"
                    )
            )
    )
    private List<User> cancellations;

    public void addToCancellationHistory(User user) {
        cancellations.add(user);
        user.getCancellations().add(this);
    }


}
