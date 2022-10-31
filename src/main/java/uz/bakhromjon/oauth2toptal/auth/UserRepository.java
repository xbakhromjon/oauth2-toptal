package uz.bakhromjon.oauth2toptal.auth;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author : Bakhromjon Khasanboyev
 * @since : 31/10/22, Mon, 14:12
 **/
@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    Page<User> findAllByEmail(String auth, Pageable pageable);

    Page<User> findAllByEmailContainsAndEmail(String email, String auth, Pageable pageable);

    Page<User> findByEmailContains(String email, Pageable pageable);

    Optional<User> findByEmail(String email);
}
