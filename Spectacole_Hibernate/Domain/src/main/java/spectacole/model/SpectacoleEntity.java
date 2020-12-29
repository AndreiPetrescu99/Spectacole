package spectacole.model;


import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Objects;

public class SpectacoleEntity implements Serializable {


    private Long id;
    private OffsetDateTime dataSpectacol;
    private String titlu;
    private Long pretBilet;

    public SpectacoleEntity(Long id, OffsetDateTime dataSpectacol, String titlu, Long pretBilet) {
        this.id = id;
        this.dataSpectacol = dataSpectacol;
        this.titlu = titlu;
        this.pretBilet = pretBilet;
    }

    public SpectacoleEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OffsetDateTime getDataSpectacol() {
        return dataSpectacol;
    }

    public void setDataSpectacol(OffsetDateTime dataSpectacol) {
        this.dataSpectacol = dataSpectacol;
    }

    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public Long getPretBilet() {
        return pretBilet;
    }

    public void setPretBilet(Long pretBilet) {
        this.pretBilet = pretBilet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpectacoleEntity that = (SpectacoleEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(dataSpectacol, that.dataSpectacol) &&
                Objects.equals(titlu, that.titlu) &&
                Objects.equals(pretBilet, that.pretBilet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dataSpectacol, titlu, pretBilet);
    }

    @Override
    public String toString() {
        return "SpectacoleEntity{" +
                "id=" + id +
                ", dataSpectacol=" + dataSpectacol +
                ", titlu='" + titlu + '\'' +
                ", pretBilet=" + pretBilet +
                '}';
    }
}
