package spectacole.model;

import java.time.OffsetDateTime;
import java.util.Objects;

public class VerificareEntity {

    private Long id;
    private SpectacoleEntity spectacol;
    private OffsetDateTime dateTime;
    private Long sold;
    private String salesList;
    private String status;

    public VerificareEntity(Long id, SpectacoleEntity spectacol, OffsetDateTime dateTime, Long sold, String salesList, String status) {
        this.id = id;
        this.spectacol = spectacol;
        this.dateTime = dateTime;
        this.sold = sold;
        this.salesList = salesList;
        this.status = status;
    }

    public VerificareEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SpectacoleEntity getSpectacol() {
        return spectacol;
    }

    public void setSpectacol(SpectacoleEntity spectacol) {
        this.spectacol = spectacol;
    }

    public OffsetDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(OffsetDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Long getSold() {
        return sold;
    }

    public void setSold(Long sold) {
        this.sold = sold;
    }

    public String getSalesList() {
        return salesList;
    }

    public void setSalesList(String salesList) {
        this.salesList = salesList;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VerificareEntity that = (VerificareEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(spectacol, that.spectacol) &&
                Objects.equals(dateTime, that.dateTime) &&
                Objects.equals(sold, that.sold) &&
                Objects.equals(salesList, that.salesList) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, spectacol, dateTime, sold, salesList, status);
    }

    @Override
    public String toString() {
        return "VerificareEntity{" +
                "id=" + id +
                ", spectacol=" + spectacol +
                ", dateTime=" + dateTime +
                ", sold=" + sold +
                ", salesList='" + salesList + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
