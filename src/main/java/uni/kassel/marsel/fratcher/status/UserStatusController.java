package uni.kassel.marsel.fratcher.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class UserStatusController {

    @Autowired
    private UserStatusService userStatusService;

    @RequestMapping(value = "/status", method = GET)
    public Iterable<UserStatus> getAllStatuses() {
        Iterable<UserStatus> allStatuses = userStatusService.getAllStatuses();

        return allStatuses;
    }
}
