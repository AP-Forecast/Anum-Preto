package API;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Data {

    @JsonProperty("daily")
    private DailyData daily;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DailyData {
        @JsonProperty("temperature_2m_max")
        private ArrayList<Double> temperatureMax;

        @JsonProperty("temperature_2m_min")
        private ArrayList<Double> temperatureMin;

        @JsonProperty("time")
        private ArrayList<String> time;

        @JsonProperty("apparent_temperature_max")
        private ArrayList<Double> apparentTempMax;

        @JsonProperty("apparent_temperature_min")
        private ArrayList<Double> apparentTempMin;

        @JsonProperty("uv_index_max")
        private ArrayList<Double> uvIndex;

        @JsonProperty("precipitation_sum")
        private ArrayList<Double> preciptation_sum;

        @JsonProperty("rain_sum")
        private ArrayList<Double> rain_sum;

        @JsonProperty("wind_speed_10m_max")
        private ArrayList<Double> maxWindSpeed;

        @JsonProperty("wind_direction_10m_dominant")
        private ArrayList<Double> windDirectionDegrees;

        public ArrayList<Double> getTemperatureMax() {
            return temperatureMax;
        }

        public ArrayList<Double> getTemperatureMin() {
            return temperatureMin;
        }

        public ArrayList<String> getTime() {
            return time;
        }

        @Override
        public String toString() {
            return "DailyData{" +
                    "temperatureMax=" + temperatureMax +
                    ", temperatureMin=" + temperatureMin +
                    ", time=" + time +
                    ", apparentTempMax=" + apparentTempMax +
                    ", apparentTempMin=" + apparentTempMin +
                    ", uvIndex=" + uvIndex +
                    ", preciptation_sum=" + preciptation_sum +
                    ", rain_sum=" + rain_sum +
                    ", maxWindSpeed=" + maxWindSpeed +
                    ", windDirectionDegrees=" + windDirectionDegrees +
                    '}';
        }
    }

    public DailyData getDaily() {
        return daily;
    }

    @Override
    public String toString() {
        return "Data{" +
                "daily=" + daily +
                '}';
    }
}
