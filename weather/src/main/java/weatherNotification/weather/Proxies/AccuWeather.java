package weatherNotification.weather.Proxies;

import org.springframework.web.client.RestTemplate;
import weatherNotification.weather.Model.DTO.WeatherData;

import java.util.HashMap;

public class AccuWeather implements IWeather{
    private String apiToken = "WSOyODbXh1Jbea1lMwNaFArQRg1GgFDN";
    private String locationId = "349269";
    private String baseUrl = "http://dataservice.accuweather.com/currentconditions/v1/";
    private String optionalParamters = "language=es-ES&details=true";

    @Override
    public HashMap<String, Object> getData(){
        HashMap<String, Object> response = new HashMap<>();

        try{
            RestTemplate restTemplate = new RestTemplate();
            WeatherData[] weatherResult = restTemplate.getForObject(this.getEndpoint(), WeatherData[].class);
            response.put("result", weatherResult[0]);
            response.put("error", false);
            response.put("message", "");
        } catch (Exception e) {
            response.put("result", null);
            response.put("error", true);
            response.put("message", e.getMessage());
        }

        return response;
    }

    private String getEndpoint(){
        return this.baseUrl + this.locationId + "?apikey=" + this.apiToken + "&" +optionalParamters;
    }

}
