package com.example.demo.ServiciosImplementacion;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class WeatherService {
    
    @Value("${weather.api.key}")
    private String apiKey;
    
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather";
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    
    public WeatherService() {
        this.restTemplate = new RestTemplate();
        this.objectMapper = new ObjectMapper();
    }
    
    public String getWeatherForCity(String city) {
        try {
            if (apiKey.isEmpty()) {
                return "⚠️ API Key no configurada. Regístrate en OpenWeatherMap";
            }
            
            String url = String.format("%s?q=%s&appid=%s&units=metric&lang=es", 
                                      BASE_URL, city, apiKey);
            
            System.out.println("Consultando clima para: " + city);
            
            String response = restTemplate.getForObject(url, String.class);
            
            if (response != null) {
                JsonNode json = objectMapper.readTree(response);
                
                // Extraer temperatura
                double temp = json.path("main").path("temp").asDouble();
                
                // Extraer descripción del clima
                String description = json.path("weather").get(0).path("description").asText();
                
                // Extraer humedad
                int humidity = json.path("main").path("humidity").asInt();
                
                // Extraer velocidad del viento
                double windSpeed = json.path("wind").path("speed").asDouble();
                
                String emoji = getWeatherEmoji(description);
                
                return String.format("%s %s | 🌡️ %.1f°C | 💧 %d%% | 💨 %.1f km/h", 
                                    emoji, description, temp, humidity, windSpeed);
            }
            
            return "No se pudo obtener el clima";
            
        } catch (Exception e) {
            System.out.println("Error al consultar clima: " + e.getMessage());
            return "❌ Ciudad no encontrada o error de conexión";
        }
    }
    
    private String getWeatherEmoji(String description) {
        String desc = description.toLowerCase();
        if (desc.contains("soleado") || desc.contains("clear")) return "☀️";
        if (desc.contains("nublado") || desc.contains("cloud")) return "☁️";
        if (desc.contains("lluvia") || desc.contains("rain")) return "🌧️";
        if (desc.contains("tormenta") || desc.contains("storm")) return "⛈️";
        if (desc.contains("nieve") || desc.contains("snow")) return "❄️";
        if (desc.contains("niebla") || desc.contains("fog")) return "🌫️";
        return "🌡️";
    }
    
    public String getWorkoutRecommendation(String weather) {
        if (weather.contains("Ciudad no encontrada")) {
            return "❌ No se encontró la ciudad. Verifica el nombre.";
        }
        
        if (weather.contains("API Key no configurada")) {
            return "⚠️ Configura la API key de OpenWeatherMap en application.properties";
        }
        
        String lowerWeather = weather.toLowerCase();
        
        if (lowerWeather.contains("soleado") || lowerWeather.contains("clear")) {
            return "☀️ ¡Perfecto para entrenar al aire libre! Corre o haz ejercicios en el parque 🏃‍♂️";
        } 
        else if (lowerWeather.contains("lluvia") || lowerWeather.contains("rain")) {
            return "🌧️ Día lluvioso - Mejor entrenar en casa: Yoga, pesas o HIIT 🧘‍♂️";
        } 
        else if (lowerWeather.contains("nublado") || lowerWeather.contains("cloud")) {
            return "⛅ Clima fresco - Ideal para caminar largas distancias o montar en bici 🚴‍♂️";
        } 
        else if (lowerWeather.contains("nieve") || lowerWeather.contains("snow")) {
            return "❄️ ¡Está nevando! Entrenamiento en interior: saltos, sentadillas y cardio 🔥";
        } 
        else if (lowerWeather.contains("tormenta") || lowerWeather.contains("storm")) {
            return "⚡ Tormenta eléctrica - Mejor entrenar en casa y mantenerte seguro 🏠";
        }
        else if (lowerWeather.contains("niebla") || lowerWeather.contains("fog")) {
            return "🌫️ Poca visibilidad - Entrenamiento en interior recomendado 💪";
        }
        else {
            return "🏋️ Entrenamiento normal recomendado. ¡A por todas! 💪";
        }
    }
}