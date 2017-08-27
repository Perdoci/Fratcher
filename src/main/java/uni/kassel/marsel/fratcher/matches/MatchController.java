package uni.kassel.marsel.fratcher.matches;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class MatchController {

    @Autowired
    private MatchService matchService;


    @RequestMapping(value = "/filter/match", method = GET)
    public List<Match> getAllStatusesForMe() {

        //get all my matches
        return matchService.findMyMatches();
    }

}
