package uni.kassel.marsel.fratcher.matches;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uni.kassel.marsel.fratcher.message.Message;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class MatchController {

    @Autowired
    private MatchService matchService;

}
