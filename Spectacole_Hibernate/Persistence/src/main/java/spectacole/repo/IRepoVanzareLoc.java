package spectacole.repo;

import spectacole.model.VanzareEntity;
import spectacole.model.VanzareLocEntity;

import java.util.List;

public interface IRepoVanzareLoc {
    VanzareLocEntity save(VanzareLocEntity vanzareLoc);
    List<VanzareLocEntity> findLocByVanzare(VanzareEntity vanzareEntity);
    int size();
}
