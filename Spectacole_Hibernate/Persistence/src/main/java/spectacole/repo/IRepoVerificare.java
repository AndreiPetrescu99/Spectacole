package spectacole.repo;

import spectacole.model.VerificareEntity;

public interface IRepoVerificare {
    int size();
    VerificareEntity save(VerificareEntity verificare);
}
