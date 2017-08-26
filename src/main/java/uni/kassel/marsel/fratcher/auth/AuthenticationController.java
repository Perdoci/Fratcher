package uni.kassel.marsel.fratcher.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uni.kassel.marsel.fratcher.user.User;
import uni.kassel.marsel.fratcher.user.UserService;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class AuthenticationController {


    @Autowired
    private AuthenticationService authenticationService;


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

    @RequestMapping(value = "/api/login", method = POST)
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

    @RequestMapping(value = "/api/register", method = POST)
    public ResponseEntity<Object> handleUserRegistration(@RequestBody UserEmailStatus userEmailStatus) {

        String email = userEmailStatus.getEmail();
        String pass = userEmailStatus.getPass();
        String status = userEmailStatus.getStatus();

        Boolean saved = authenticationService.handleUserRegistration(email, pass, status);
        if(saved){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
