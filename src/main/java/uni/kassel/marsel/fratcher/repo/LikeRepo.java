package uni.kassel.marsel.fratcher.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import uni.kassel.marsel.fratcher.interaction.Like;

import java.util.List;

public interface LikeRepo extends CrudRepository<Like, Long>{

  /*  @Query("SELECT l from LikeStatus l WHERE l.giver = :giver AND l.taker = :taker")
    List<Like> findOne(@Param("giver") Long giver, @Param("taker") Long taker);
*/

    Like findLikeByGiverAndTaker(@Param("giver") Long giver, @Param("taker") Long taker);

    List<Like> findAllByGiver(@Param("giver") Long giver);
}
