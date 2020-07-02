package weatherNotification.businessLogic.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import weatherNotification.businessLogic.Models.WeatherData;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/manage")
public class BusinessLogicController {

    @RequestMapping(value = "/publishWeather", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> publishWeather() {
        Map<String, Object> response = new HashMap<String, Object>();
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> weatherData = restTemplate.getForObject("http://localhost:8080/weather/get", Map.class);
        Boolean error = (Boolean) weatherData.get("error");
        if(!error){
            ObjectMapper mapper = new ObjectMapper();
            WeatherData weatherDTO = mapper.convertValue(weatherData.get("result"), WeatherData.class);
            HashMap<String, Object> request = new HashMap<String, Object>();
            request.put("message", weatherDTO.getMessage());
            request.put("service", "twitter");
            response = restTemplate.postForObject("http://localhost:8082/notification/publish", request, Map.class);
        }else{
            response = weatherData;
        }

        return response;
    }
}
