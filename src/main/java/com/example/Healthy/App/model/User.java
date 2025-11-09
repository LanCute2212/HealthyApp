package com.example.Healthy.App.model;
import jakarta.persistence.* ;
import lombok.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "age")
    private Double age;

    @Column(name = "gender")
    private String gender;

    @Column(name = "height")
    private Double height;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "levelActivity")
    private Double levelActivity;

    @Column(name= "goal")
    private Double goal;

    @Column(name = "email")
    private String email;

    @Column(name = "phoneNumber")
    private Long phoneNumber;

    @Column(name = "bmi")
    private Double bmi;

    @Column(name = "bmr")
    private Double bmr;

    @Column(name = "tdee")
    private Double tdee;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private List<ActivityLog>  activityLogs;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private List<FoodLog> foodLogs;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private List<Blog> blogs;

    @ManyToOne
    @JoinColumn(name = "role_id")
    @ToString.Exclude
    private Role role;

    @ManyToMany
    @JoinTable(name = "user_workout", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "work_id"))
    @ToString.Exclude
    private List<Workout> workouts;
}
