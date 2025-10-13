package com.example.Healthy.App.model;
import lombok.*;
import jakarta.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "MealDetail")
public class MealDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "dish_id")
    private Dish dish;

    @ManyToOne
    @JoinColumn(name = "foodLog_id")
    private FoodLog foodLog;

    @Column(name = "foodIntake")
    private Double foodIntake;

}
