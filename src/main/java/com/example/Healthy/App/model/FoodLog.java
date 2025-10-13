package com.example.Healthy.App.model;
import lombok.*;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "FoodLog")
public class FoodLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "logTime")
    private LocalDateTime logTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "meal")
    private Meal meal;
}
