package uni.kassel.marsel.fratcher.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uni.kassel.marsel.fratcher.interaction.Dislike;
import uni.kassel.marsel.fratcher.interaction.DislikeService;
import uni.kassel.marsel.fratcher.interaction.Like;
import uni.kassel.marsel.fratcher.interaction.LikeService;
import uni.kassel.marsel.fratcher.user.UserService;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class UserStatusController {

    @Autowired
    private UserStatusService userStatusService;

    @Autowired
    private UserService userService;

    @Autowired
    private LikeService likeService;

    @Autowired
    private DislikeService dislikeService;

    @RequestMapping(value = "/filter/status", method = GET)
    public Iterable<UserStatus> getAllStatuses() {
        Iterable<UserStatus> allStatuses = userStatusService.getAllStatuses();

        return allStatuses;
    }

    @RequestMapping(value = "/filter/status/{id}", method = GET)
    public UserStatus getAllStatuses(@PathVariable Long id) {
        UserStatus userStatus = userStatusService.getStatusByID(id);

        return userStatus;
    }

    //TODO add response statuses
    @RequestMapping(value = "/filter/status/{id}/like", method = POST)
    public  void addLiKe(@PathVariable Long id) {

        likeService.addLike(id);

    }

    @RequestMapping(value = "/filter/status/{id}/dislike", method = POST)
    public void giveLive(@PathVariable Long id) {

        dislikeService.addDislike(id);
    }

}
