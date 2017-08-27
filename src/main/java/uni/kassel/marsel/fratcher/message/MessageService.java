package uni.kassel.marsel.fratcher.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uni.kassel.marsel.fratcher.matches.Match;
import uni.kassel.marsel.fratcher.matches.MatchService;
import uni.kassel.marsel.fratcher.repo.MessageRepo;
import uni.kassel.marsel.fratcher.user.UserService;

@Service
public class MessageService {

    @Autowired
    private UserService userService;

    @Autowired
    private MatchService matchService;

    @Autowired
    private MessageRepo messageRepo;


    public Boolean addMessage(Long matchId, Message newText) {

        Match matchById = matchService.findMatchById(matchId);

        if(matchById.getUsers().contains(userService.getCurrentUser())){
            Message message = new Message();
            message.setText(newText.getText());
            message.setOwner(userService.getCurrentUser());
            messageRepo.save(message);

            return matchService.addMessage(matchId, message);
        }

        return false;
    }
}
