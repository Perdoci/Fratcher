package uni.kassel.marsel.fratcher.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uni.kassel.marsel.fratcher.interaction.Like;
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

    public Iterable<UserStatus> getAllStatuses() {
        // LOG.info("Returning posts. user={}", userService.getCurrentUser().getEmail());
        return statusRepo.getAllStatuses();
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
