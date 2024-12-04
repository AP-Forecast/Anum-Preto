package API;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.Assert.*;

public class DataTest {

    // Classe Localizer
    public static class Localizer {
        private static String latitude;
        private static String longitude;

        public static String[] getWeeklyData() throws Exception {
            try {
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder().uri(URI.create(
                        "https://api.open-meteo.com/v1/forecast?latitude=" + latitude +
                                "&longitude=" + longitude +
                                "&daily=temperature_2m_max,temperature_2m_min&timezone=America%2FSao_Paulo"
                )).build();
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                ObjectMapper mapper = new ObjectMapper();

                if (response.statusCode() == 200) {
                    // Simulando a leitura do corpo da resposta
                    return new String[]{"code: " + response.statusCode(), "message: success"};
                } else {
                    return new String[]{"code: " + response.statusCode(), "message: failed"};
                }

            } catch (Exception e) {
                return null;
            }
        }

        public static String[] getHourlyData() throws Exception {
            try {
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder().uri(URI.create(
                        "https://api.open-meteo.com/v1/forecast?latitude=" + latitude +
                                "&longitude=" + longitude +
                                "&hourly=temperature_2m&timezone=America%2FSao_Paulo"
                )).build();
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                if (response.statusCode() == 200) {
                    return new String[]{"code: " + response.statusCode(), "message: success"};
                } else {
                    return new String[]{"code: " + response.statusCode(), "message: failed"};
                }

            } catch (Exception e) {
                return null;
            }
        }

        public static void setLocation(String lat, String longi) {
            latitude = lat;
            longitude = longi;
        }
    }

    // Testes
    @Before
    public void setUp() {
        // Definindo coordenadas para o teste
        Localizer.setLocation("-15.791599", "-47.8921089");
    }

    @Test
    public void testGetWeeklyData_Success() throws Exception {
        // Simulando uma resposta bem-sucedida
        String[] result = Localizer.getWeeklyData();

        assertNotNull(result);
        assertEquals("code: 200", result[0]);
        assertEquals("message: success", result[1]);
    }

    @Test
    public void testGetWeeklyData_Failure() throws Exception {
        // Alterando as coordenadas para um valor inválido
        Localizer.setLocation("invalid_latitude", "invalid_longitude");

        String[] result = Localizer.getWeeklyData();

        assertNotNull(result);
        assertEquals("code: 404", result[0]);
        assertEquals("message: failed", result[1]);
    }

    @Test
    public void testGetHourlyData_Success() throws Exception {
        // Simulando uma resposta bem-sucedida
        String[] result = Localizer.getHourlyData();

        assertNotNull(result);
        assertEquals("code: 200", result[0]);
        assertEquals("message: success", result[1]);
    }

    @Test
    public void testGetHourlyData_Failure() throws Exception {
        // Alterando as coordenadas para um valor inválido
        Localizer.setLocation("invalid_latitude", "invalid_longitude");

        String[] result = Localizer.getHourlyData();

        assertNotNull(result);
        assertEquals("code: 404", result[0]);
        assertEquals("message: failed", result[1]);
    }

    @Test
    public void testSetLocation() {
        Localizer.setLocation("34.0522", "-118.2437");

        // Verifica se as coordenadas foram definidas corretamente
        assertEquals("34.0522", Localizer.latitude);
        assertEquals("-118.2437", Localizer.longitude);
    }
}
