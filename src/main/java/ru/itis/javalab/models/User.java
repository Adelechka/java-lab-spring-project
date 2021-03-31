package ru.itis.javalab.models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "account",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "login", name = "uniqueLoginConstraint"),
                @UniqueConstraint(columnNames = "email", name = "uniqueEmailConstraint")
        }
)
public class User implements Serializable {

    private static final long serialVersionUID = -8352317829229800107L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private Integer age;

    @Column(unique = true)
    private String login;

    @Column(unique = true)
    private String email;

    private String hashPassword;

    @Enumerated(value = EnumType.STRING)
    private Status status;

    private String confirmCode;

    @Enumerated(value = EnumType.STRING)
    private State state;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    public Boolean isActive() {
        return this.state == State.ACTIVE;
    }

    public Boolean isBanned() {
        return this.state == State.BANNED;
    }

    public Boolean isAdmin() {
        return this.role == Role.ADMIN;
    }

    public enum Status {
        CONFIRMED, NOT_CONFIRMED
    }

    public enum State {
        ACTIVE, BANNED
    }

    public enum Role {
        USER, ADMIN
    }

    @OneToMany(mappedBy = "author")
    private List<Post> posts;

    @OneToMany(mappedBy = "author")
    private List<Comment> comments;

    @ManyToMany
    @JoinTable(name = "upvoted_post",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "post_id", referencedColumnName = "id")}
            )
    private List<Post> upvoted;
}
