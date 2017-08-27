package uni.kassel.marsel.fratcher.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import uni.kassel.marsel.fratcher.matches.Match;
import uni.kassel.marsel.fratcher.user.User;

import java.util.List;

public interface MatchRepo extends CrudRepository<Match, Long>{

    @Query("SELECT p FROM Match p WHERE :user  MEMBER OF p.users")
    List<Match> findMyMatches(@Param("user") User user);
}
