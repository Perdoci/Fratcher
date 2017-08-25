package uni.kassel.marsel.fratcher.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import uni.kassel.marsel.fratcher.user.User;

public interface UserRepo extends CrudRepository<User, Long>{

    User findByEmailAndPassword(@Param("email") String email,
                                   @Param("password") String password);
}
