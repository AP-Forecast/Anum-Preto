package API;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HourlyData {

    @JsonProperty("hourly")
    private Hourly hourly;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Hourly {

        @JsonProperty("time")
        private ArrayList<String> time;

        @JsonProperty("temperature_2m")
        private ArrayList<Double> temperature;

        @JsonProperty("precipitation_probability")
        private ArrayList<Integer> rainChance;

        @JsonProperty("rain")
        private ArrayList<Double> rainAmount;

        @JsonProperty("wind_speed_10m")
        private ArrayList<Double> windSpeed;

        @JsonProperty("wind_direction_10m")
        private ArrayList<Integer> windDirection;

        @JsonProperty("uv_index")
        private ArrayList<Double> uvIndex;

        public ArrayList<String> getTime() {
            return time;
        }

        public ArrayList<Double> getTemperature() {
            return temperature;
        }

        public ArrayList<Integer> getRainChance() {
            return rainChance;
        }

        public ArrayList<Double> getRainAmount() {
            return rainAmount;
        }

        public ArrayList<Double> getWindSpeed() {
            return windSpeed;
        }

        public ArrayList<Integer> getWindDirection() {
            return windDirection;
        }

        public ArrayList<Double> getUvIndex() {
            return uvIndex;
        }
    }

    public Hourly getHourly() {
        return hourly;
    }
}
