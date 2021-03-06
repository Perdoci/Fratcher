package uni.kassel.marsel.fratcher.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import uni.kassel.marsel.fratcher.interaction.Dislike;
import uni.kassel.marsel.fratcher.interaction.Like;

import java.util.List;

public interface DislikeRepo extends CrudRepository<Dislike, Long>{

    Dislike findDislikeByGiverAndTaker(@Param("giver") Long giver, @Param("taker") Long taker);

    List<Dislike> findAllByGiver(@Param("giver") Long giver);
}
