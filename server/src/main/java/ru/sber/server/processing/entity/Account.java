package ru.sber.server.processing.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Accounts")
@NoArgsConstructor
@Getter
@Setter
public class Account {
    @Id
    private Long id;
    private String number;
    private int balance;
    private String currency;
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client_id;
    @OneToMany(mappedBy = "account_id")
    private Set<Card> cards;
}
