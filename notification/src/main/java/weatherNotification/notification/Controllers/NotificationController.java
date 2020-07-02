package weatherNotification.notification.Controllers;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import weatherNotification.notification.Models.INotification;
import weatherNotification.notification.Models.TwitterService;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    @RequestMapping(value = "/publish", method = RequestMethod.POST)
    public HashMap<Object, Object> publishTweet(@RequestBody Map<String, String> request){
        HashMap<Object, Object> response = new HashMap<Object, Object>();

        if(request.get("service").equals("twitter")){
            INotification notification = new TwitterService();
            response = notification.publishNotification((String) request.get("message"));
        }else{
            response.put("error", true);
            response.put("message", "The service provide not exist");
        }

        return response;
    }
}
