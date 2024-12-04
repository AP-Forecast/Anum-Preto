package API.database.models;

import jakarta.persistence.*;

@Entity
@Table(name = "hourly")
public class Hourly {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "day_id", foreignKey = @ForeignKey(name = "FK_DAY_HOUR"), nullable = false)
    private Daily dayId;

    @Column(name = "hour", nullable = false)
    private String hour;

    @Column(name = "temperature", nullable = false)
    private Double temperature;

    @Column(name = "rainChance", nullable = false)
    private Integer rainChance;

    @Column(name = "rainAmount", nullable = false)
    private Double rainAmount;

    @Column(name = "windSpeed", nullable = false)
    private Double windSpeed;

    @Column(name = "windDirection", nullable = false)
    private Integer windDirection;

    @Column(name = "uvIndex", nullable = false)
    private Double uvIndex;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Daily getDayId() {
        return dayId;
    }

    public void setDayId(Daily dayId) {
        this.dayId = dayId;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Integer getRainChance() {
        return rainChance;
    }

    public void setRainChance(Integer rainChance) {
        this.rainChance = rainChance;
    }

    public Double getRainAmount() {
        return rainAmount;
    }

    public void setRainAmount(Double rainAmount) {
        this.rainAmount = rainAmount;
    }

    public Double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(Double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public Integer getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(Integer windDirection) {
        this.windDirection = windDirection;
    }

    public Double getUvIndex() {
        return uvIndex;
    }

    public void setUvIndex(Double uvIndex) {
        this.uvIndex = uvIndex;
    }
}
