package uni.kassel.marsel.fratcher.matches;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uni.kassel.marsel.fratcher.message.Message;
import uni.kassel.marsel.fratcher.repo.MatchRepo;
import uni.kassel.marsel.fratcher.user.User;
import uni.kassel.marsel.fratcher.user.UserService;

import java.util.List;

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

    public Boolean addMessage(Long matchId, Message message) {

        Match match = matchRepo.findOne(matchId);

        if (match == null) {
            throw new IllegalArgumentException("Match not found. id=" + matchId);
        }

        match.addMessage(message);
        matchRepo.save(match);

        if(match.getId() != null){
            return true;
        }
        return false;
    }

    public MatchIdAndUser findMyMatches() {

        List<Match> myMatches = matchRepo.findMyMatches(userService.getCurrentUser());
        MatchIdAndUser matchIdAndUser = new MatchIdAndUser();
        for (Match match: myMatches) {
            matchIdAndUser.id = match.getId();

            for (User user:  match.getUsers()) {
                if(user.getId() != userService.getCurrentUser().getId()){
                    matchIdAndUser.email = user.getEmail();
                }
            }
        }

        return matchIdAndUser;
    }

    public List<Message> getComments(Long id) {
        Match matchById = matchRepo.findMatchById(id);
        List<Message> messageList = matchById.getMessageList();

        return messageList;
    }

    public Match findMatchById(Long matchId) {
        Match matchById = matchRepo.findMatchById(matchId);

        return matchById;
    }

    public static class MatchIdAndUser {
        public Long id;
        public String email;
    }
}
