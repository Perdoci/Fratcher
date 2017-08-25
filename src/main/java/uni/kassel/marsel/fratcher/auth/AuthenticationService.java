package uni.kassel.marsel.fratcher.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uni.kassel.marsel.fratcher.repo.UserRepo;
import uni.kassel.marsel.fratcher.status.UserStatusService;
import uni.kassel.marsel.fratcher.user.User;
import uni.kassel.marsel.fratcher.user.UserService;

@Service
public class AuthenticationService {

    @Autowired
    private UserService userService;

    @Autowired
    private UserStatusService userStatusService;

    @Autowired
    private UserRepo userRepo;


    public Boolean handleUserLogin(String email, String pass) {

        //look if user exists
        Boolean found = userService.findUserByEmailAndPass(email, pass);
        if (found != null) {
            return true;
        }
        return false;
    }

    public Boolean handleUserRegistration(String email, String pass, String status) {

        Long statusId = null;
        User user = new User();
        user.setEmail(email);
        user.setPassword(pass);
        Long userId = userService.addUser(user);
        if (userId != null) {
            statusId = userStatusService.setStatusOfUser(status, user);
        }
        if (statusId != null) {
            return true;
        }
        return false;
    }
}
