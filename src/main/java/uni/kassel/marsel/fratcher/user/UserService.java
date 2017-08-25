package uni.kassel.marsel.fratcher.user;

import org.springframework.beans.factory.annotation.Autowired;
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

    public Boolean findUserByEmailAndPass(String email, String pass) {

        User user = userRepo.findByEmailAndPassword(email, pass);
        if(user != null){
            return true;
        }
        return false;
    }
}
