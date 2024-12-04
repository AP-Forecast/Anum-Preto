package API.database.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "daily")
public class Daily {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @OneToMany(mappedBy = "dayId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Hourly> hourlyID = new ArrayList<>();

    @Column(name = "temperatureMax", nullable = false)
    private Double temperatureMax;

    @Column(name = "temperatureMin", nullable = false)
    private Double temperatureMin;

    @Column(name = "time", nullable = false)
    private String time;

    @Column(name = "apparentTempMax", nullable = false)
    private Double apparentTempMax;

    @Column(name = "apparentTempMin", nullable = false)
    private Double apparentTempMin;

    @Column(name = "uvIndex", nullable = false)
    private Double uvIndex;

    @Column(name = "preciptationSum", nullable = false)
    private Double preciptationSum;

    @Column(name = "rainSum", nullable = false)
    private Double rainSum;

    @Column(name = "maxWindSpeed", nullable = false)
    private Double maxWindSpeed;

    @Column(name = "domWindDirection", nullable = false)
    private Double domWindDirection;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Hourly> getHourlyID() {
        return hourlyID;
    }

    public void setHourlyID(Hourly hourlyID) {
        this.hourlyID.add(hourlyID);
    }

    public Double getTemperatureMax() {
        return temperatureMax;
    }

    public void setTemperatureMax(Double temperatureMax) {
        this.temperatureMax = temperatureMax;
    }

    public Double getTemperatureMin() {
        return temperatureMin;
    }

    public void setTemperatureMin(Double temperatureMin) {
        this.temperatureMin = temperatureMin;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Double getApparentTempMax() {
        return apparentTempMax;
    }

    public void setApparentTempMax(Double apparentTempMax) {
        this.apparentTempMax = apparentTempMax;
    }

    public Double getApparentTempMin() {
        return apparentTempMin;
    }

    public void setApparentTempMin(Double apparentTempMin) {
        this.apparentTempMin = apparentTempMin;
    }

    public Double getUvIndex() {
        return uvIndex;
    }

    public void setUvIndex(Double uvIndex) {
        this.uvIndex = uvIndex;
    }

    public Double getPreciptationSum() {
        return preciptationSum;
    }

    public void setPreciptationSum(Double preciptationSum) {
        this.preciptationSum = preciptationSum;
    }

    public Double getRainSum() {
        return rainSum;
    }

    public void setRainSum(Double rainSum) {
        this.rainSum = rainSum;
    }

    public Double getMaxWindSpeed() {
        return maxWindSpeed;
    }

    public void setMaxWindSpeed(Double maxWindSpeed) {
        this.maxWindSpeed = maxWindSpeed;
    }

    public Double getDomWindDirection() {
        return domWindDirection;
    }

    public void setDomWindDirection(Double domWindDirection) {
        this.domWindDirection = domWindDirection;
    }
}
