package uni.kassel.marsel.fratcher.interaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uni.kassel.marsel.fratcher.repo.DislikeRepo;
import uni.kassel.marsel.fratcher.status.UserStatus;
import uni.kassel.marsel.fratcher.status.UserStatusService;
import uni.kassel.marsel.fratcher.user.UserService;

import java.util.ArrayList;
import java.util.List;

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

    public List<UserStatus> getStatusesIDisliked(Long id) {
        List<Dislike> statusesILiked = dislikeRepo.findAllByGiver(id);
        List<UserStatus> userStatusesIDisliked = new ArrayList<>();

        //for each liked status of me, search the
        for (Dislike dislike: statusesILiked) {
            Long taker = dislike.getTaker();
            UserStatus statusByID = userStatusService.getStatusByID(taker);
            userStatusesIDisliked.add(statusByID);
        }
        return userStatusesIDisliked;
    }
}
