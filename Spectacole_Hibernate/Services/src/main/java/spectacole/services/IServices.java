package spectacole.services;

import spectacole.model.SpectacoleEntity;

import java.rmi.RemoteException;
import java.util.List;

public interface IServices {
    String buyTickets(List<Integer> locuri, SpectacoleEntity spectacol) throws RemoteException;
    List<SpectacoleEntity> getSpectacole() throws RemoteException;
}
