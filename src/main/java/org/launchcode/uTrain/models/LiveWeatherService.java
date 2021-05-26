package org.launchcode.uTrain.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import java.math.BigDecimal;
import java.net.URI;

@Service
public class LiveWeatherService {

//    private static final String WEATHER_URL = "http://api.openweathermap.org/data/2.5/weather?q={city},{country}&APPID={key}&units=metric";

    private static final String WEATHER_URL = "http://api.openweathermap.org/data/2.5/weather?zip={zipCode},{country}&APPID={key}&units=imperial";

    @Value("${api.openweathermap.key}")
    private String apiKey;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;


    public LiveWeatherService(RestTemplateBuilder restTemplateBuilder, ObjectMapper objectMapper) {
        this.restTemplate = restTemplateBuilder.build();
        this.objectMapper = objectMapper;
    }

//    public CurrentWeather getCurrentWeather(String city, String country) {
//        URI url = new UriTemplate(WEATHER_URL).expand(city, country, apiKey);
//        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
//
//        return convert(response);
//    }

    public CurrentWeather getCurrentWeather(Integer zipCode, String country) throws JsonProcessingException {
        URI url = new UriTemplate(WEATHER_URL).expand(zipCode, country, apiKey);
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        return convert(response);
    }

    private CurrentWeather convert(ResponseEntity<String> response) {
        try {
            JsonNode root = objectMapper.readTree(response.getBody());
            return new CurrentWeather(root.path("weather").get(0).path("main").asText(),
                    BigDecimal.valueOf(root.path("main").path("humidity").asDouble()),
                    BigDecimal.valueOf(root.path("main").path("temp").asDouble()),
                    BigDecimal.valueOf(root.path("main").path("feels_like").asDouble()),
                    BigDecimal.valueOf(root.path("wind").path("speed").asDouble()),
                    BigDecimal.valueOf(root.path("coord").path("lon").asDouble()),
                    BigDecimal.valueOf(root.path("coord").path("lat").asDouble()),
                    BigDecimal.valueOf(root.path("sys").path("sunrise").asDouble()),
                    BigDecimal.valueOf(root.path("sys").path("sunset").asDouble()),
                    BigDecimal.valueOf(root.path("main").path("temp_min").asDouble()),
                    BigDecimal.valueOf(root.path("main").path("temp_max").asDouble()));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error parsing JSON", e);
        }
    }
//    public CurrentWeather convert(ResponseEntity<String> response) throws JsonProcessingException {
//        Main main = new Main();
//        JsonNode node = objectMapper.readTree(response.getBody());
//
//        // try catch block
//        JsonNode colorNode = node.get("temp");
//        double temp = colorNode.asDouble();
//        main.setTemp(temp);
//        return main;
//    }

//    private WeatherResponse convert(ResponseEntity<String> response) {
//            JsonNode root = objectMapper.readTree(response.getBody());
//
//    }
}
