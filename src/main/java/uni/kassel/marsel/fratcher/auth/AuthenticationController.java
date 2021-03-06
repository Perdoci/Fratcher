package uni.kassel.marsel.fratcher.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uni.kassel.marsel.fratcher.status.UserStatusService;
import uni.kassel.marsel.fratcher.user.User;
import uni.kassel.marsel.fratcher.user.UserService;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class AuthenticationController {


    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserStatusService userStatusService;


    public static class UserEmail{

        private String email;
        private String pass;

        public String getEmail() {
            return email;
        }

        public String getPass() {
            return pass;
        }
    }
    public static class UserEmailStatus{
        private String email;
        private String pass;
        private String status;

        public String getEmail() {
            return email;
        }

        public String getPass() {
            return pass;
        }

        public String getStatus() {
            return status;
        }
    }

    @RequestMapping(value = "/login", method = POST)
    public ResponseEntity<AuthenticationService.UserToken> handleUserLogin(@RequestBody UserEmail userEmail) {

        String email = userEmail.getEmail();
        String pass = userEmail.getPass();


        AuthenticationService.UserToken token = authenticationService.handleUserLogin(email, pass);
        if(token != null){
            //user authenticated - found in the DB
            return new ResponseEntity<>(token,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @RequestMapping(value = "/register", method = POST)
    public ResponseEntity<AuthenticationService.UserToken> handleUserRegistration(@RequestBody UserEmailStatus userEmailStatus) {

        AuthenticationService.UserToken userToken = null;
        String email = userEmailStatus.getEmail();
        String pass = userEmailStatus.getPass();
        String status = userEmailStatus.getStatus();
        User userByEmailAndPass = userService.findUserByEmail(email);

        if(userByEmailAndPass == null){
           userToken = authenticationService.handleUserRegistration(email, pass, status);

            userStatusService.setStatusOfUser(status,userService.findUserByEmailAndPass(email,pass) );
        }
        if(userToken != null){
            return new ResponseEntity<>(userToken, HttpStatus.OK);
        }
        return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
    }
}
