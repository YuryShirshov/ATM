package ru.sber.server.processing.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Cards")
@NoArgsConstructor
@Getter
@Setter
public class Card {
    @Id
    private Long id;
    String number;
    int pin;
    int cvc2;
    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account_id;
}
