package uni.kassel.marsel.fratcher.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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


    public Boolean addMessage(Long matchId, Message newMessage) {

        Message message = new Message();
        message.setText(newMessage.getText());
        message.setOwner(userService.getCurrentUser());
        messageRepo.save(message);

        return matchService.addMessage(matchId, message);

    }
}
