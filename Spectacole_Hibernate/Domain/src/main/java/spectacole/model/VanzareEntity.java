package spectacole.model;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Objects;

public class VanzareEntity implements Serializable {


    private Long id;
    private Long idTranzactie;
    private SpectacoleEntity spectacol;
    private OffsetDateTime dataVanzare;

    public VanzareEntity(Long id, Long idTranzactie, SpectacoleEntity idSpectacol, OffsetDateTime dataVanzare) {
        this.id = id;
        this.idTranzactie = idTranzactie;
        this.spectacol = idSpectacol;
        this.dataVanzare = dataVanzare;
    }

    public VanzareEntity(){}


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SpectacoleEntity getSpectacol() {
        return spectacol;
    }

    public void setSpectacol(SpectacoleEntity idSpectacol) {
        this.spectacol = idSpectacol;
    }

    public OffsetDateTime getDataVanzare() {
        return dataVanzare;
    }

    public void setDataVanzare(OffsetDateTime dataVanzare) {
        this.dataVanzare = dataVanzare;
    }

    public Long getIdTranzactie() {
        return idTranzactie;
    }

    public void setIdTranzactie(Long idTranzactie) {
        this.idTranzactie = idTranzactie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VanzareEntity that = (VanzareEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(idTranzactie, that.idTranzactie) &&
                Objects.equals(spectacol, that.spectacol) &&
                Objects.equals(dataVanzare, that.dataVanzare);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idTranzactie, spectacol, dataVanzare);
    }

    @Override
    public String toString() {
        return "VanzareEntity{" +
                "id=" + id +
                ", idTranzactie=" + idTranzactie +
                ", spectacol=" + spectacol +
                ", dataVanzare=" + dataVanzare +
                '}';
    }
}
