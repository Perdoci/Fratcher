package uni.kassel.marsel.fratcher.matches;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uni.kassel.marsel.fratcher.repo.MatchRepo;
import uni.kassel.marsel.fratcher.user.User;
import uni.kassel.marsel.fratcher.user.UserService;

@Service
public class MatchService {

    @Autowired
    private MatchRepo matchRepo;

    @Autowired
    private UserService userService;

    public void addMatch(Long likeGiver, Long likeTaker) {

        Match match = new Match();

        User user1 = userService.findUserById(likeGiver);
        User user2 = userService.findUserById(likeTaker);

        match.addUser(user1);
        match.addUser(user2);

        matchRepo.save(match);
    }
}
