package ru.itis.javalab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.javalab.models.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository <User, Long> {
    List<User> findAllByFirstNameIsLikeIgnoreCaseAndAgeAfter(String firstName, int age);

    @Query("select user from User user where user.age >= :age " +
            "and (upper(user.firstName) like concat('%', upper(:name), '%') " +
            "       or upper(user.lastName) like  concat('%', upper(:name), '%'))")
    List<User> search(@Param("name") String name, @Param("age") Integer age);

    User findByConfirmCode(String confirmCode);
    Optional<User> findByLogin(String login);

    @Transactional
    @Modifying
    @Query("update User u set u.status = ?2 where u.id = ?1")
    void updateStatus(Long id, User.Status status);
}
