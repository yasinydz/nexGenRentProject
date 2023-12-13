package com.nexgencarrental.nexGenCarRental.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="cars")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name ="kilometer")
    private int kilometer;

    @Column(name = "year")
    private int year;

    @Column(name ="daily_price")
    private double dailyPrice;

    @Column(name = "plate",unique = true)
    private String plate;

    @ManyToOne
    @JoinColumn(name ="model_id")
    @JsonIgnore
    private Model model;

    @ManyToOne
    @JoinColumn(name ="color_id")
    @JsonIgnore
    private Color color;

    @OneToMany(mappedBy = "car")
    @JsonIgnore
    private List<Rental> rentals;

}
