package uni.kassel.marsel.fratcher.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uni.kassel.marsel.fratcher.interaction.DislikeService;
import uni.kassel.marsel.fratcher.interaction.LikeService;
import uni.kassel.marsel.fratcher.repo.StatusRepo;
import uni.kassel.marsel.fratcher.user.User;
import uni.kassel.marsel.fratcher.user.UserService;

import java.util.List;
import java.util.Random;

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

    public UserStatus getOneStatusForMe() {
        // LOG.info("Returning posts. user={}", userService.getCurrentUser().getEmail());

        Long id = userService.getCurrentUser().getId();
        //get the statuses without my status
        List<UserStatus> allStatusesExceptMine = statusRepo.findUserStatusByIdIsNot(id);

        //get all statuses I liked
        List<UserStatus> statusesILiked = likeService.getStatusesILiked(id);

        //get all statuses I disliked
        List<UserStatus> statusesIDisliked = dislikeService.getStatusesIDisliked(id);

        allStatusesExceptMine.removeAll(statusesILiked);
        allStatusesExceptMine.removeAll(statusesIDisliked);

        UserStatus randomStatusFromList = getRandomStatusFromList(allStatusesExceptMine);

        return randomStatusFromList;
    }

    private UserStatus getRandomStatusFromList(List<UserStatus> allStatusesExceptMe) {
        Random randomGenerator = new Random();
        int index = randomGenerator.nextInt(allStatusesExceptMe.size());
        UserStatus userStatus = allStatusesExceptMe.get(index);
        return userStatus;
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
        //User owner = userstatus.getOwner();
        return owner.getId();
    }

    public static class StatusUserID {
         public String status;
         public Long owner;
    }

}
