package uni.kassel.marsel.fratcher.auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uni.kassel.marsel.fratcher.repo.UserRepo;
import uni.kassel.marsel.fratcher.status.UserStatusService;
import uni.kassel.marsel.fratcher.user.User;
import uni.kassel.marsel.fratcher.user.UserService;

@Service
public class AuthenticationService {

    @Value("${authenticationService.jwt.secret}")
    private String secret;

    @Value("${authenticationService.salt}")
    private String salt;

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
        String hashedPassword = hashPassword(pass);
        User user = userService.findUserByEmailAndPass(email, hashedPassword);
        if(user!=null){
            String token = Jwts.builder()
                    .setSubject(email)
                    .setId(user.getId().toString())
                    .signWith(SignatureAlgorithm.HS512, secret)
                    .compact();

            UserToken userToken = new UserToken();
            userToken.user = user;
            userToken.token = token;

            //set the logged in user as the current user
            setUser(user.getId(), user.getEmail());
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

    /**
     * Return a password hashed with SHA-512.
     *
     * @param password plain text password
     * @return hashed password
     */
    public String hashPassword(String   password) {
        return DigestUtils.sha512Hex(salt + password);

    }
}
