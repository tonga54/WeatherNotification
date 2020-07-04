package weatherNotification.weather.Models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class WeatherData {

    public String getWeatherText() {
        return weatherText;
    }

    public void setWeatherText(String weatherText) {
        this.weatherText = weatherText;
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

    @JsonAlias("WeatherText")
    private String weatherText;

    @JsonAlias("HasPrecipitation")
    private Boolean itsRaining;

    @JsonAlias("UVIndex")
    private Double uvIndex;

    @JsonAlias("UVIndexText")
    private String uvIndexText;

    private Double temperature;
    private Double thermalSensation;
    private Double wind;


    @SuppressWarnings("unchecked")
    @JsonProperty("Temperature")
    private void unpackNestedTemperature(Map<String,Object> temperature) {
        Map<String,Double> owner = (Map<String,Double>)temperature.get("Metric");
        this.temperature = owner.get("Value");
    }

    @SuppressWarnings("unchecked")
    @JsonProperty("RealFeelTemperature")
    private void unpackNestedRealFeelTemperature(Map<String,Object> realFeelTemperature) {
        Map<String,Double> owner = (Map<String,Double>)realFeelTemperature.get("Metric");
        this.thermalSensation = owner.get("Value");
    }

    @SuppressWarnings("unchecked")
    @JsonProperty("Wind")
    private void unpackNestedWind(Map<String,Object> wind) {
        Map<Object,Object> windSpeed = (Map<Object, Object>) wind.get("Speed");
        Map<Object,Object> windSpeedMetric = (Map<Object, Object>) windSpeed.get("Metric");
        this.wind = (Double) windSpeedMetric.get("Value");
    }
}

