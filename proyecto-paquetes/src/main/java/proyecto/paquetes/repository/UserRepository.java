package proyecto.paquetes.repository;

import proyecto.paquetes.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findFirst3ByEmailIgnoreCaseContaining(String partialEmailAddress);

    User findFirstByEmailIgnoreCaseContaining(String partialEmailAddress);

    Boolean existsByEmail(String partialEmailAddress);
}
