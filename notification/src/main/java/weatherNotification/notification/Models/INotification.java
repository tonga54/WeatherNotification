package weatherNotification.notification.Models;

import java.util.HashMap;

public interface INotification {
    public HashMap<Object, Object> publishNotification(String message);
}
