package com.isa.bloodtransfusion.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "questionnaires")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Questionnaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "answer_1")
    private boolean answer1;

    @Column(name = "answer_2")
    private boolean answer2;

    @Column(name = "answer_3")
    private boolean answer3;

    @Column(name = "answer_4")
    private boolean answer4;

    @Column(name = "answer_5")
    private boolean answer5;

    @Column(name = "answer_6")
    private boolean answer6;

    @Column(name = "answer_7")
    private boolean answer7;

    @Column(name = "answer_8")
    private boolean answer8;

    @Column(name = "answer_9")
    private boolean answer9;

    @Column(name = "answer_10")
    private boolean answer10;

    @Column(name = "answer_11")
    private boolean answer11;

    @Column(name = "answer_12")
    private boolean answer12;

    @Column(name = "answer_13")
    private boolean answer13;

    @Column(name = "answer_14")
    private boolean answer14;

    @Column(name = "answer_15")
    private boolean answer15;

    @Column(name = "answer_16")
    private boolean answer16;

    @Column(name = "answer_17")
    private boolean answer17;

    @Column(name = "answer_18")
    private boolean answer18;

    @Column(name = "answer_19")
    private boolean answer19;

    @Column(name = "answer_20")
    private boolean answer20;

    @Column(name = "answer_21")
    private boolean answer21;

    @Column(name = "answer_22")
    private boolean answer22;

    @Column(name = "answer_23")
    private boolean answer23;

    @Column(name = "answer_24")
    private boolean answer24;

    @Column(name = "answer_25")
    private boolean answer25;

    @Column(name = "answer_26")
    private boolean answer26;

    @ManyToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "id"
    )
    private User user;


}
