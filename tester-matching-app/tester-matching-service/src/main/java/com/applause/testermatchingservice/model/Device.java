package com.applause.testermatchingservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "devices")
public class Device {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "description")
    private String description;

    @ManyToMany(fetch = FetchType.EAGER, targetEntity = Tester.class, mappedBy = "devices", cascade = CascadeType.ALL)
    private Set<Tester> testers;

    @OneToMany(fetch = FetchType.EAGER, targetEntity = Bug.class, mappedBy = "device", cascade = CascadeType.ALL)
    private Set<Bug> bugs;

    public Device(int id, String description) {
        this.id = id;
        this.description = description;
        this.testers = new LinkedHashSet<>();
        this.bugs = new LinkedHashSet<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Device device = (Device) o;
        return Objects.equals(description, device.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }
}
