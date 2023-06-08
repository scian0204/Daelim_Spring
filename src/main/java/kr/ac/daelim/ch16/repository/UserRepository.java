package kr.ac.daelim.ch16.repository;

import kr.ac.daelim.ch16.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User getByUid(String uid);
}
