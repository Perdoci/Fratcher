package uni.kassel.marsel.fratcher.auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uni.kassel.marsel.fratcher.repo.UserRepo;
import uni.kassel.marsel.fratcher.status.UserStatusService;
import uni.kassel.marsel.fratcher.user.User;
import uni.kassel.marsel.fratcher.user.UserService;

@Service
public class AuthenticationService {

    private String secret = "Severus Snape was a good guy!";


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
    public void setUser(Long id, String email) {
        User user = new User();
        user.setId(id);
        user.setEmail(email);
        UsernamePasswordAuthenticationToken secAuth = new UsernamePasswordAuthenticationToken(user, null);
        SecurityContextHolder.getContext().setAuthentication(secAuth);
    }


    public UserToken handleUserLogin(String email, String pass) {
        User user = userService.findUserByEmailAndPass(email, pass);
        if(user!=null){
            String secret = "Severus Snape was a good guy!";
            String token = Jwts.builder()
                    .setSubject(email)
                    .setId(user.getId().toString())
                    .signWith(SignatureAlgorithm.HS512, secret)
                    .compact();

            UserToken userToken = new UserToken();
            userToken.user = user;
            userToken.token = token;
            return userToken;
        }

        return null;
    }

    public Boolean handleUserRegistration(String email, String pass, String status) {

        User user = new User();
        user.setEmail(email);
        user.setUserPass(pass);
        Long userId = userService.addUser(user);

       if(userId != null){
           return true;
       }
       return false;
    }

    public Object parseToken(String jwtToken) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parse(jwtToken)
                .getBody();
    }
}
