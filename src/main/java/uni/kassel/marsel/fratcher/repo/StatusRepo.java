package uni.kassel.marsel.fratcher.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import uni.kassel.marsel.fratcher.status.UserStatus;

import java.util.List;

public interface StatusRepo  extends CrudRepository<UserStatus, Long>{

    @Query("SELECT s FROM UserStatus s")
    List<UserStatus> getAllStatuses();

    List<UserStatus> findUserStatusById(@Param("id") Long id);

}
