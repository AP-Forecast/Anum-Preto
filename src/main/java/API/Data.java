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

        @Override
        public String toString() {
            return "DailyData{" +
                    "temperatureMax=" + temperatureMax +
                    ", temperatureMin=" + temperatureMin +
                    ", time=" + time +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Data{" +
                "daily=" + daily +
                '}';
    }
}
