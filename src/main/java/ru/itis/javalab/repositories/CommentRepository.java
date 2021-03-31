package ru.itis.javalab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.javalab.models.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
