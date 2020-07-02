package weatherNotification.businessLogic.Models;

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

    private String weatherText;
    private Boolean itsRaining;
    private Double temperature;
    private Double thermalSensation;

    @SuppressWarnings("unchecked")
    @JsonProperty("result")
    private void unpackNestedResult(Map<String,Object> result) {
        this.weatherText = (String) result.get("weatherText");
        this.itsRaining = (Boolean) result.get("itsRaining");
        this.temperature = (Double) result.get("temperature");
        this.thermalSensation = (Double) result.get("thermalSensation");
    }

    @Override
    public String getMessage() {
        String message = "Estado actual: " + this.weatherText + "\n";
        message += this.getThermalSensationMessage() + "\n";
        message += this.itsRaining ? "No olvides llevar ☂️" : "Que raro? no esta lloviendo!! 🥳🎉";

        return message;
    }

    private String getThermalSensationMessage(){
        String message = "Sensacion termica: " + this.thermalSensation + "ºC ";


        if(this.thermalSensation <= 13){
            message += "⛄️❄️";
//        }else if(this.thermalSensation <= 13){
//            message += "";
        }else if(this.thermalSensation <= 19){
            message += "😬🌥";
        }else{
            message += "😎☀️";
        }

        return message;
    }
}
