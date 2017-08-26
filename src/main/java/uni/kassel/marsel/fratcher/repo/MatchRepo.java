package uni.kassel.marsel.fratcher.repo;

import org.springframework.data.repository.CrudRepository;
import uni.kassel.marsel.fratcher.matches.Match;

public interface MatchRepo extends CrudRepository<Match, Long>{


}
