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
@Table(name = "ActivityLog")
public class ActivityLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer logid;

    @Column(name ="durationMinutes")
    private Double durationMinutes;

    @Column(name = "logDate")
    private LocalDateTime logDate;

    @Column(name = "caloriesBurned")
    private Double caloriesBurned;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "activity_id")
    private Activity activity;

}