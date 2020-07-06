package weatherNotification.weather.Libraries;

import com.amazonaws.services.sqs.model.MessageAttributeValue;
import org.springframework.cloud.aws.messaging.core.MessageAttributeDataTypes;
import weatherNotification.weather.Model.DTO.WeatherData;

import java.util.HashMap;
import java.util.Map;

public class SqsParser {

    public static Map<String, MessageAttributeValue> parseMapToSqsAttributes(Map<String, Object> oldMap){
        Map<String, MessageAttributeValue> newMap = new HashMap<String, MessageAttributeValue>();
        WeatherData weatherData = (WeatherData) oldMap.get("result");

        MessageAttributeValue thermalSensation = new MessageAttributeValue();
        thermalSensation.setStringValue(String.valueOf(weatherData.getThermalSensation()));
        thermalSensation.setDataType(MessageAttributeDataTypes.NUMBER);
        newMap.put("thermalSensation", thermalSensation);

        MessageAttributeValue wind = new MessageAttributeValue();
        wind.setStringValue(String.valueOf(weatherData.getWind()));
        wind.setDataType(MessageAttributeDataTypes.NUMBER);
        newMap.put("wind", wind);

        MessageAttributeValue temperature = new MessageAttributeValue();
        temperature.setStringValue(String.valueOf(weatherData.getTemperature()));
        temperature.setDataType(MessageAttributeDataTypes.NUMBER);
        newMap.put("temperature", temperature);

        MessageAttributeValue itsRaining = new MessageAttributeValue();
        itsRaining.setStringValue(String.valueOf(weatherData.getItsRaining()));
        itsRaining.setDataType(MessageAttributeDataTypes.STRING);
        newMap.put("itsRaining", itsRaining);

        MessageAttributeValue weatherText = new MessageAttributeValue();
        weatherText.setStringValue(String.valueOf(weatherData.getWeatherText()));
        weatherText.setDataType(MessageAttributeDataTypes.STRING);
        newMap.put("weatherText", weatherText);

        MessageAttributeValue uvIndex = new MessageAttributeValue();
        uvIndex.setStringValue(String.valueOf(weatherData.getUvIndex()));
        uvIndex.setDataType(MessageAttributeDataTypes.NUMBER);
        newMap.put("uvIndex", uvIndex);

        MessageAttributeValue uvIndexText = new MessageAttributeValue();
        uvIndexText.setStringValue(String.valueOf(weatherData.getUvIndexText()));
        uvIndexText.setDataType(MessageAttributeDataTypes.STRING);
        newMap.put("uvIndexText", uvIndexText);

        return newMap;
    }
}
