package ru.sber.server.processing.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "Clients")
@NoArgsConstructor
@Getter
@Setter
public class Client {
    @Id
    private Long id;
    private String name;
    private int age;
    @OneToMany(mappedBy = "client_id")
    private Set<Account> accounts;
}
