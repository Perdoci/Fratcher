package uni.kassel.marsel.fratcher.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uni.kassel.marsel.fratcher.matches.MatchService;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class MessageController {

    @Autowired
    private MatchService matchService;

    @Autowired
    private MessageService messageService;


    /**TODO stringify the message in the frontend as message object in order to be mapped
     * Reason: Causing HTTP Message not readable exception(does not harm the faultless
     * working of the app)
     *
     * @param id
     * @param message
     * @return
     */

    @RequestMapping(value = "/filter/match/{id}/message", method = POST)
    public ResponseEntity<Object> addLiKe(@PathVariable Long id, @RequestBody Message message) {

        Boolean added = messageService.addMessage(id, message);

       if(added){
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
