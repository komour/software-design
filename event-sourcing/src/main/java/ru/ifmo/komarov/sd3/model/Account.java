package ru.ifmo.komarov.sd3.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Account {
    @Id
    private final Long id;

    public Account() {
        id = -1L;
    }

    public Account(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
