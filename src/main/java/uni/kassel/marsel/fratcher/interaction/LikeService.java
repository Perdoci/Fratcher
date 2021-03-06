package uni.kassel.marsel.fratcher.interaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uni.kassel.marsel.fratcher.matches.MatchService;
import uni.kassel.marsel.fratcher.repo.LikeRepo;
import uni.kassel.marsel.fratcher.status.UserStatus;
import uni.kassel.marsel.fratcher.status.UserStatusService;
import uni.kassel.marsel.fratcher.user.User;
import uni.kassel.marsel.fratcher.user.UserService;

import java.util.ArrayList;
import java.util.List;

@Service
public class LikeService {

    @Autowired
    private LikeRepo likeRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private UserStatusService userStatusService;

    @Autowired
    private MatchService matchService;

    public User addLike(Long id) {

        Long likeTaker = userStatusService.findStatusOwnerIdByStatusId(id);
        User userLiked = userService.getUserById(likeTaker);
        Long likeGiver = userService.getCurrentUser().getId();

        Like likeFound = likeRepo.findLikeByGiverAndTaker(likeGiver, likeTaker);

        //find out if the other user likes me
        Like reverseLike = likeRepo.findLikeByGiverAndTaker(likeTaker, likeGiver);

        //if the like does't exist in the repo, we add it, otherwise we ignore it
        if(likeFound == null && likeTaker != likeGiver){
            Like like = new Like();
            like.setTaker(likeTaker);
            like.setGiver(likeGiver);
            likeRepo.save(like);

            //create match
            if(reverseLike != null){
                matchService.addMatch(likeGiver, likeTaker);
                return userLiked;

            }
        }
        return null;
    }

    public List<UserStatus> getStatusesILiked(Long id) {
        List<Like> statusesILiked = likeRepo.findAllByGiver(id);
        List<UserStatus> userStatusesILiked = new ArrayList<>();

        //for each liked status of me, search the
        for (Like like: statusesILiked) {
            Long taker = like.getTaker();
            UserStatus statusByID = userStatusService.getStatusByID(taker);
            userStatusesILiked.add(statusByID);
        }
        return userStatusesILiked;
    }
}
