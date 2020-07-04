package weatherNotification.businessLogic.Models;

import java.util.Calendar;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class WeatherData implements IMessage {
    public String getWeatherText() {
        return weatherText;
    }

    public Boolean getItsRaining() {
        return itsRaining;
    }

    public void setItsRaining(Boolean itsRaining) {
        this.itsRaining = itsRaining;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getThermalSensation() {
        return thermalSensation;
    }

    public void setThermalSensation(Double thermalSensation) {
        this.thermalSensation = thermalSensation;
    }

    public void setWeatherText(String weatherText) {
        this.weatherText = weatherText;
    }

    public Double getUvIndex() {
        return uvIndex;
    }

    public void setUvIndex(Double uvIndex) {
        this.uvIndex = uvIndex;
    }

    public String getUvIndexText() {
        return uvIndexText;
    }

    public void setUvIndexText(String uvIndexText) {
        this.uvIndexText = uvIndexText;
    }

    public Double getWind() {
        return wind;
    }

    public void setWind(Double wind) {
        this.wind = wind;
    }

    private String weatherText;
    private Boolean itsRaining;
    private Double temperature;
    private Double thermalSensation;
    private Double uvIndex;
    private String uvIndexText;
    private Double wind;

    @SuppressWarnings("unchecked")
    @JsonProperty("result")
    private void unpackNestedResult(Map<String,Object> result) {
        this.weatherText = (String) result.get("weatherText");
        this.itsRaining = (Boolean) result.get("itsRaining");
        this.temperature = (Double) result.get("temperature");
        this.uvIndex = (Double) result.get("uvIndex");
        this.uvIndexText = (String) result.get("uvIndexText");
        this.wind = (Double) result.get("wind");
    }

    @Override
    public String getMessage() {
        String message = "Estado actual: " + this.weatherText + "\n";
        message += this.getThermalSensationMessage();
        message += this.itsRaining ? "No olvides llevar ☂️\n" : "";
        message += this.getWindSpeedMessage();
        message += this.getUvIndexMessage();
        return message;
    }

    private String getThermalSensationMessage(){
        String message = "Sensación térmica: " + this.thermalSensation + "ºC ";

        if(this.thermalSensation <= 3){
            message+= "🥶🧊";
        }else if(this.thermalSensation <= 13){
            message += "⛄️❄️";
        }else if(this.thermalSensation <= 19){
            message += "😬🌥";
        }else if(this.thermalSensation <= 26){
            message += "😎☀️";
        }else{
            message += "🥵🏖";
        }

        return message + "\n";
    }

    private String getUvIndexMessage(){
        String message = "";
        Calendar now = Calendar.getInstance();
        Integer currentMonth = now.get(Calendar.MONTH) + 1;
        if(currentMonth > 10 && currentMonth < 3){
            message = "Índice UV: " + this.getUvIndexText() + " " + this.getUvIndex() + " 🧴🌞\n";
        }

        return message;
    }

    private String getWindSpeedMessage(){
        String message = "";
        if(this.wind > 40){
            message += "Cuidado con el viento (velocidad): " + this.wind + "km/h 💨🌪\n";
        }

        return message;
    }
}
