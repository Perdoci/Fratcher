package uni.kassel.marsel.fratcher.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uni.kassel.marsel.fratcher.interaction.Dislike;
import uni.kassel.marsel.fratcher.interaction.Like;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class UserStatusController {

    @Autowired
    private UserStatusService userStatusService;

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


    @RequestMapping(value = "/filter/status/{id}/like", method = POST)
    public UserStatus giveLive(@PathVariable Long id, @RequestBody Like like) {
       // UserStatus userStatus = userStatusService.getStatusByID(id);

        return null;
    }

    @RequestMapping(value = "/filter/status/{id}/dislike", method = POST)
    public UserStatus giveLive(@PathVariable Long id, @RequestBody Dislike dislike) {
        // UserStatus userStatus = userStatusService.getStatusByID(id);

        return null;
    }


}
