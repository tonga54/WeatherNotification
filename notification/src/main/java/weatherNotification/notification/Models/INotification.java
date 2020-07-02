package weatherNotification.notification.Models;

import java.util.HashMap;

public interface INotification {
    public HashMap<String, Object> publishNotification(String message);
}
