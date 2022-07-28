package com.applause.testermatchingservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "testers")
public class Tester {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "country")
    private String country;

    @Column(name = "last_login", columnDefinition = "TIMESTAMP")
    private LocalDateTime lastLogin;

    @ManyToMany(fetch = FetchType.EAGER, targetEntity = Device.class, cascade = CascadeType.ALL)
    @JoinTable(
            name = "tester_device",
            joinColumns = @JoinColumn(name = "tester_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "device_id", referencedColumnName = "id"))
    private Set<Device> devices;

    @OneToMany(fetch = FetchType.EAGER, targetEntity = Bug.class, mappedBy = "tester", cascade = CascadeType.ALL)
    private Set<Bug> bugs;

    public Tester(int id, String firstName, String lastName, String country, LocalDateTime lastLogin) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.lastLogin = lastLogin;
        this.devices = new LinkedHashSet<>();
        this.bugs = new LinkedHashSet<>();
    }
}
