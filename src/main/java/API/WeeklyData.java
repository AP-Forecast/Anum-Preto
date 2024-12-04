package API;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeeklyData {

    @JsonProperty("daily")
    private Weekly daily;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Weekly {
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

        public void setTemperatureMax(ArrayList<Double> temperatureMax) {
            this.temperatureMax = temperatureMax;
        }

        public ArrayList<Double> getTemperatureMin() {
            return temperatureMin;
        }

        public void setTemperatureMin(ArrayList<Double> temperatureMin) {
            this.temperatureMin = temperatureMin;
        }

        public ArrayList<String> getTime() {
            return time;
        }

        public void setTime(ArrayList<String> time) {
            this.time = time;
        }

        public ArrayList<Double> getApparentTempMax() {
            return apparentTempMax;
        }

        public void setApparentTempMax(ArrayList<Double> apparentTempMax) {
            this.apparentTempMax = apparentTempMax;
        }

        public ArrayList<Double> getApparentTempMin() {
            return apparentTempMin;
        }

        public void setApparentTempMin(ArrayList<Double> apparentTempMin) {
            this.apparentTempMin = apparentTempMin;
        }

        public ArrayList<Double> getUvIndex() {
            return uvIndex;
        }

        public void setUvIndex(ArrayList<Double> uvIndex) {
            this.uvIndex = uvIndex;
        }

        public ArrayList<Double> getPreciptation_sum() {
            return preciptation_sum;
        }

        public void setPreciptation_sum(ArrayList<Double> preciptation_sum) {
            this.preciptation_sum = preciptation_sum;
        }

        public ArrayList<Double> getRain_sum() {
            return rain_sum;
        }

        public void setRain_sum(ArrayList<Double> rain_sum) {
            this.rain_sum = rain_sum;
        }

        public ArrayList<Double> getMaxWindSpeed() {
            return maxWindSpeed;
        }

        public void setMaxWindSpeed(ArrayList<Double> maxWindSpeed) {
            this.maxWindSpeed = maxWindSpeed;
        }

        public ArrayList<Double> getWindDirectionDegrees() {
            return windDirectionDegrees;
        }

        public void setWindDirectionDegrees(ArrayList<Double> windDirectionDegrees) {
            this.windDirectionDegrees = windDirectionDegrees;
        }
    }

    public Weekly getDaily() {
        return daily;
    }

    public void setDaily(Weekly daily) {
        this.daily = daily;
    }
}
