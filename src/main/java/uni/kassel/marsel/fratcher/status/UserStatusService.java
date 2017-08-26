package uni.kassel.marsel.fratcher.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uni.kassel.marsel.fratcher.repo.StatusRepo;
import uni.kassel.marsel.fratcher.user.User;

@Service
public class UserStatusService {
    @Autowired
    private StatusRepo statusRepo;

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

        return statusRepo.findUserStatusesById(id);
    }
}
