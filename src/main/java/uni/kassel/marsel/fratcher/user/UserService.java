package uni.kassel.marsel.fratcher.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uni.kassel.marsel.fratcher.repo.UserRepo;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public Long addUser(User user) {

        userRepo.save(user);

        return user.getId();
    }

    public User findUserByEmailAndPass(String email, String pass) {

        User byEmailAndPassword = userRepo.findByEmailAndPassword(email, pass);
        return byEmailAndPassword;

    }

    public User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
