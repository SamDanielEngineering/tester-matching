package com.applause.testermatchingservice.model;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "bugs")
public class Bug {

    @Id
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Device.class, cascade = CascadeType.ALL)
    private Device device;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Tester.class, cascade = CascadeType.ALL)
    private Tester tester;

}
