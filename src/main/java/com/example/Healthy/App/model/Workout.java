package com.example.Healthy.App.model;
import lombok.*;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "Workout")

public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "linkVideo")
    private String linkVideo;

    @Column(name = "duration")
    private Double duration;

    @Column(name = "caloriesBurned")
    private Double caloriesBurned;

    @ManyToOne
    @JoinColumn(name = "trainingMode_id", referencedColumnName = "id")
    private TrainingMode trainingMode;
}
