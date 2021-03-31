package ru.itis.javalab.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;

    @Column(length = 1024)
    private String text;

    @ManyToMany(mappedBy = "upvoted")
    private List<User> upvoted;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments;
}
