package weatherNotification.notification.Models;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import java.util.HashMap;

public class TwitterService implements INotification{

    @Override
    public HashMap<Object, Object> publishNotification(String message) {
        HashMap<Object, Object> response = new HashMap<Object,Object>();
        try{
            Twitter twitter = TwitterFactory.getSingleton();
            Status status = twitter.updateStatus(message);
            response.put("error", false);
            response.put("result", status.getText());
        }catch (TwitterException e){
            response.put("error", false);
            response.put("result", e.getMessage());
        }

        return response;
    }
}

