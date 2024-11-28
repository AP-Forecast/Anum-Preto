package API;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Localizer {
    private String latitude;
    private String longitude;

    Data data = new Data();

    public Localizer(String latitude, String longitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Data getLocation() throws Exception{
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.
                    newBuilder().uri(URI.create(
                            "https://api.open-meteo.com/v1/forecast?latitude="+ this.latitude +
                                    "&longitude="+ this.longitude +
                                    "&daily=temperature_2m_max,temperature_2m_min,apparent_temperature_max,apparent_temperature_min,sunrise,sunset,uv_index_max,precipitation_sum,rain_sum,wind_speed_10m_max,wind_direction_10m_dominant&timezone=America%2FSao_Paulo"
                    )).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            ObjectMapper mapper = new ObjectMapper();

            if (response.statusCode() == 200){
                data = mapper.readValue(response.body(), Data.class);
                System.out.println(response.body());
                return data;
            } else {
                System.out.println(response.statusCode());
                return null;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public String getData() {
        return data.toString();
    }
}
