package weatherNotification.weather.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import weatherNotification.weather.Models.WeatherData;

import java.util.HashMap;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public HashMap<String, Object> getData(){
        HashMap<String, Object> response = new HashMap<>();
        try{
            RestTemplate restTemplate = new RestTemplate();
            WeatherData[] weatherResult = restTemplate.getForObject("http://dataservice.accuweather.com/currentconditions/v1/349269?apikey=SwD3TiFuolztx02eWc6Bah4ThX0GAiVJ&language=es-ES&details=true", WeatherData[].class);
            WeatherData data = weatherResult[0];
            response.put("result", data);
            response.put("error", false);
            response.put("message", "");
        } catch (Exception e) {
            response.put("result", null);
            response.put("error", true);
            response.put("message", e.getMessage());
        }

        return response;
    }
}
