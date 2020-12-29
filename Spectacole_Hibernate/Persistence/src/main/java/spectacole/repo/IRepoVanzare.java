package spectacole.repo;

import spectacole.model.SpectacoleEntity;
import spectacole.model.VanzareEntity;

import java.util.List;

public interface IRepoVanzare {
    VanzareEntity save(VanzareEntity vanzare);
    List<VanzareEntity> findVanzariBySpectacol(SpectacoleEntity spectacoleEntity);
    VanzareEntity findLastTransaction();
    int size();
}
