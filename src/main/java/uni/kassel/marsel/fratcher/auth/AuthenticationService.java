package uni.kassel.marsel.fratcher.auth;

import org.springframework.beans.factory.annotation.Autowired;
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

    public static class UserToken {
        public User user;
        public String token;
    }

    public UserToken handleUserLogin(String email, String pass) {
        User user = userService.findUserByEmailAndPass(email, pass);
        if(user!=null){
            AuthenticationService.UserToken token = new AuthenticationService.UserToken();
            token.user = user;
            token.token = "<JWT-TOKEN>";
            return token;
        }

        return null;
    }

    public Boolean handleUserRegistration(String email, String pass, String status) {

        //TODO look if user already exists
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
