package uni.kassel.marsel.fratcher.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import uni.kassel.marsel.fratcher.user.User;

public interface UserRepo extends CrudRepository<User, Long>{

    User findByEmailAndUserPass(@Param("email") String email,
                                   @Param("userPass") String password);

    User findByEmail(@Param("email") String email);

    User findById(@Param("id") Long id);
}
