package uni.kassel.marsel.fratcher.repo;

import org.springframework.data.repository.CrudRepository;
import uni.kassel.marsel.fratcher.message.Message;

public interface MessageRepo extends CrudRepository<Message, Long>{


}
