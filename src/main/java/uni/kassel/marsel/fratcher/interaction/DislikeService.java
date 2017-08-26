package uni.kassel.marsel.fratcher.interaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uni.kassel.marsel.fratcher.repo.DislikeRepo;

@Service
public class DislikeService {

    @Autowired
    private DislikeRepo dislikeRepo;
}
