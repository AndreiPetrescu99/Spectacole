package spectacole.repo;

import spectacole.model.SpectacoleEntity;

import java.util.List;

public interface IRepoSpectacole {
    int size();
    SpectacoleEntity save(SpectacoleEntity spectacol);
    List<SpectacoleEntity> findSpectacole();
}
