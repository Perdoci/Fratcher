package uni.kassel.marsel.fratcher.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uni.kassel.marsel.fratcher.interaction.DislikeService;
import uni.kassel.marsel.fratcher.interaction.Like;
import uni.kassel.marsel.fratcher.interaction.LikeService;
import uni.kassel.marsel.fratcher.repo.StatusRepo;
import uni.kassel.marsel.fratcher.user.User;
import uni.kassel.marsel.fratcher.user.UserService;

import java.util.List;

@Service
public class UserStatusService {
    @Autowired
    private StatusRepo statusRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private LikeService likeService;

    @Autowired
    private DislikeService dislikeService;

    public List<UserStatus> getAllStatusesForMe() {
        // LOG.info("Returning posts. user={}", userService.getCurrentUser().getEmail());

        //owner id in the user status table is like the id of the user status itself
        //due to one2one relation scheme
        Long id = userService.getCurrentUser().getId();
        //get the statuses without my status
        List<UserStatus> allStatusesExceptMe = statusRepo.findUserStatusByIdIsNot(id);

        //get all statuses I liked
        List<UserStatus> statusesILiked = likeService.getStatusesILiked(id);

        //get all statuses I disliked
        List<UserStatus> statusesIDisliked = dislikeService.getStatusesIDisliked(id);

        //remove the 2 latter results from the first and
        //there are only statuses left with which i had no interaction with
        allStatusesExceptMe.removeAll(statusesILiked);
        allStatusesExceptMe.removeAll(statusesIDisliked);

        return allStatusesExceptMe;
    }


    public Long setStatusOfUser(String status, User user) {

        UserStatus userStatus = new UserStatus();
        userStatus.setOwner(user);
        userStatus.setStatus(status);
        statusRepo.save(userStatus);

        return userStatus.getId();
    }

    public UserStatus getStatusByID(Long id) {
        List<UserStatus> userStatusById = statusRepo.findUserStatusById(id);

        return userStatusById.get(0);
    }

    public Long findStatusOwnerIdByStatusId(Long id) {
        List<UserStatus> userstatus = statusRepo.findUserStatusById(id);
        User owner = userstatus.get(0).getOwner();
       // User userById = userService.findUserById(owner);
        //  User owner = userstatus.getOwner();
        return owner.getId();
    }

}
