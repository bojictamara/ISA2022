package com.isa.bloodtransfusion.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "complaints")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start") private LocalDateTime timestamp;

    @Column(name = "text") private String text;

    @Column(name = "answer") private String answer;

    @ManyToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "admin_id",
            nullable = true
    )
    private User admin;

    @ManyToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "customer_id",
            nullable = false
    )
    private User customer;

    @ManyToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "guilty_id",
            nullable = true
    )
    private User guilty;

    @ManyToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "center_id",
            nullable = true
    )
    Center center;

}
