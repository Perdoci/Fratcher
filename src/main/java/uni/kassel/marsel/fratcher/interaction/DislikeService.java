package uni.kassel.marsel.fratcher.interaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uni.kassel.marsel.fratcher.repo.DislikeRepo;
import uni.kassel.marsel.fratcher.status.UserStatus;
import uni.kassel.marsel.fratcher.status.UserStatusService;
import uni.kassel.marsel.fratcher.user.UserService;

@Service
public class DislikeService {

    @Autowired
    private DislikeRepo dislikeRepo;

    @Autowired
    private UserStatusService userStatusService;

    @Autowired
    private UserService userService;


    public void addDislike(Long id) {
        Long dislikeTaker = userStatusService.findStatusOwnerIdByStatusId(id);
        Long dislikeGiver = userService.getCurrentUser().getId();

        Dislike dislikeFound = dislikeRepo.findDislikeByGiverAndTaker(dislikeGiver, dislikeTaker);

        //if the dislike does't exist in the repo, we add it, otherwise we ignore it
        if(dislikeFound == null && dislikeTaker != dislikeGiver){
            Dislike dislike = new Dislike();
            dislike.setTaker(dislikeTaker);
            dislike.setGiver(dislikeGiver);
            dislikeRepo.save(dislike);
        }

    }
}
