package API;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public abstract class Localizer {
    private static String latitude;
    private static String longitude;

    private static WeeklyData data = new WeeklyData();
    private static HourlyData hourly_data = new HourlyData();

    public static String[] getWeeklyData() throws Exception{
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.
                    newBuilder().uri(URI.create(
                            "https://api.open-meteo.com/v1/forecast?latitude="+ latitude +
                                    "&longitude="+ longitude +
                                    "&daily=temperature_2m_max,temperature_2m_min,apparent_temperature_max,apparent_temperature_min,sunrise,sunset,uv_index_max,precipitation_sum,rain_sum,wind_speed_10m_max,wind_direction_10m_dominant&timezone=America%2FSao_Paulo"
                    )).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            ObjectMapper mapper = new ObjectMapper();

            if (response.statusCode() == 200){
                data = mapper.readValue(response.body(), WeeklyData.class);
                return new String[]{"code: " + response.statusCode(), "message: success", data.toString()};
            } else {
                System.out.println(response.statusCode());
                return new String[]{"code: " + response.statusCode(), "message: failed"};
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static String[] getHourlyData() throws Exception{
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.
                    newBuilder().uri(URI.create(
                            "https://api.open-meteo.com/v1/forecast?latitude=" + latitude +
                                    "&longitude=" + longitude +
                                    "&hourly=temperature_2m,precipitation_probability,rain,wind_speed_10m,wind_direction_10m,uv_index&timezone=America%2FSao_Paulo" +
                                    "&temporal_resolution=hourly_3&forecast_days=1"
                    )).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            ObjectMapper mapper = new ObjectMapper();

            if (response.statusCode() == 200){
                hourly_data = mapper.readValue(response.body(), HourlyData.class);
                return new String[]{"code: " + response.statusCode(), "message: success", hourly_data.toString()};
            } else {
                return new String[]{"code: " + response.statusCode(), "message: failed"};
            }

        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static void setLocation(String lat, String longi){
        latitude = lat;
        longitude = longi;
    }

    public static WeeklyData getData() {
        return data;
    }

    public static HourlyData getHourly_data() {
        return hourly_data;
    }
}
