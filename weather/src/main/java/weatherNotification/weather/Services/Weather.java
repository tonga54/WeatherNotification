package weatherNotification.weather.Services;

import com.amazonaws.services.sqs.model.MessageAttributeValue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import weatherNotification.weather.Libraries.SqsClient;
import weatherNotification.weather.Libraries.SqsParser;
import weatherNotification.weather.Proxies.AccuWeather;
import weatherNotification.weather.Proxies.IWeather;

import java.util.HashMap;
import java.util.Map;

@Component
public class Weather {
    private SqsClient sqsClient;

    @Autowired
    public Weather(SqsClient sqsClient){
        this.sqsClient = sqsClient;
    }

    public void getWeatherData(){
        IWeather weatherProxy = new AccuWeather();
        HashMap<String, Object> data = weatherProxy.getData();
        if(! (boolean) data.get("error")){
            Map<String, MessageAttributeValue> attributes = SqsParser.parseMapToSqsAttributes(data);
            this.sqsClient.sendMessage("WeatherData", "1", "1", attributes);
        }
    }

}
