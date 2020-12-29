package spectacole.model;


import java.io.Serializable;
import java.util.Objects;

public class VanzareLocEntity implements Serializable {

    private Long nrLoc;
    private VanzareEntity vanzare;

    public VanzareLocEntity(Long nrLoc, VanzareEntity vanzare) {
        this.nrLoc = nrLoc;
        this.vanzare = vanzare;
    }

    public VanzareLocEntity(){}

    public Long getNrLoc() {
        return nrLoc;
    }

    public void setNrLoc(Long nrLoc) {
        this.nrLoc = nrLoc;
    }

    public VanzareEntity getVanzare() {
        return vanzare;
    }

    public void setVanzare(VanzareEntity vanzare) {
        this.vanzare = vanzare;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VanzareLocEntity that = (VanzareLocEntity) o;
        return Objects.equals(nrLoc, that.nrLoc) &&
                Objects.equals(vanzare, that.vanzare);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nrLoc, vanzare);
    }

    @Override
    public String toString() {
        return "VanzareLocEntitty{" +
                "nrLoc=" + nrLoc +
                ", idVanzare=" + vanzare +
                '}';
    }
}
