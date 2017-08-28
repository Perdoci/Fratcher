package uni.kassel.marsel.fratcher.matches;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uni.kassel.marsel.fratcher.message.Message;
import uni.kassel.marsel.fratcher.message.MessageService;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class MatchController {

    @Autowired
    private MatchService matchService;

    @Autowired
    private MessageService messageService;

    
    @RequestMapping(value = "/filter/match", method = GET)
    public  List<MatchService.MatchIdAndUser> getAllMatchesForMe() {
  /*          List<Match> matches = new ArrayList<>();
        Match match = new Match();
        match.setId(12L);
        Match match2 = new Match();
        match2.setId(14L);
        Match match3 = new Match();
        match3.setId(15L);
        matches.add(match);
        matches.add(match2);
        matches.add(match3);
        return matches;*/
        //get all my matches
      return matchService.findMyMatches();
    }

    /**TODO stringify the message in the frontend as message object in order to be mapped
     * Reason: Causing HTTP Message not readable exception(does not harm the faultless
     * working of the app)
     *
     * @param id
     * @param message
     * @return
     */

    @RequestMapping(value = "/filter/match/{id}/message", method = POST)
    public ResponseEntity<Object> addMessage(@PathVariable Long id, @RequestBody Message message) {

        Boolean added = messageService.addMessage(id, message);

        if(added){
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/filter/match/{id}", method = GET)
    public List<Message> getMatchMessages(@PathVariable Long id) {

        return matchService.getComments(id);
    }

}
