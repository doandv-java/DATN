package haui.stores.repositories;

import haui.stores.data.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * The data access database
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUserNameAndStatusIs(String userName, int status);

    User findUserByUserName(String userName);
}
