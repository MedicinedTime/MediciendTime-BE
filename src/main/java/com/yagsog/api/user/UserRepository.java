package com.yagsog.api.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User save(User user); // 사용자 정보 저장
    List<User> findAll();
    Optional<User> findByCookieId(String cookieId);
}
