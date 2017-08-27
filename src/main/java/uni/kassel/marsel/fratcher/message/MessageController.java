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

    //Empty for further implementation in the future.
    //Features like deleting a message or answering a particular marked message

}
