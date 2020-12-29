package spectacole.server;

import org.graalvm.compiler.lir.sparc.SPARCArithmetic;
import spectacole.model.SpectacoleEntity;
import spectacole.model.VanzareEntity;
import spectacole.model.VanzareLocEntity;
import spectacole.model.VerificareEntity;
import spectacole.repo.IRepoSpectacole;
import spectacole.repo.IRepoVanzare;
import spectacole.repo.IRepoVanzareLoc;
import spectacole.repo.IRepoVerificare;
import spectacole.services.IServices;

import java.rmi.RemoteException;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServiceImpl implements IServices {

    private IRepoSpectacole repoSpectacole;
    private IRepoVanzare repoVanzare;
    private IRepoVanzareLoc repoVanzareLoc;
    private IRepoVerificare repoVerificare;

    private int cap = 100;
    private boolean serverOn = true;

    private final int defaultThreadCount = 5;
    private ExecutorService executor = Executors.newFixedThreadPool(defaultThreadCount);

    public ServiceImpl(IRepoSpectacole repoSpectacole, IRepoVanzare repoVanzare, IRepoVanzareLoc repoVanzareLoc, IRepoVerificare repoVerificare) {
        this.repoSpectacole = repoSpectacole;
        this.repoVanzare = repoVanzare;
        this.repoVanzareLoc = repoVanzareLoc;
        this.repoVerificare = repoVerificare;
        try {
            serverCounter();
            verify();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        List<Integer> locuri = new ArrayList<>();
//        for(int i=2; i<=66; i++){
//            locuri.add(i);
//        }
//        try {
//            String message = buyTickets(locuri, getSpectacole().get(0));
//            System.out.println(message);
//        }catch (RemoteException ex){
//            System.out.println(ex.getMessage());
//        }

    }

    public synchronized void serverCounter() throws InterruptedException{
        executor.execute(()->{
            try {
                System.out.println("Server is working...");
                //Thread.sleep(120000);
                Thread.sleep(300000);
                System.out.println("Server is shutting down...");
                serverOn = false;
                //TODO:Notify clients that server is off
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    public synchronized void verify() throws InterruptedException {
        executor.execute(()->{
            while (serverOn){
                try {
                    System.out.println("Sleeping...");
                    //Thread.sleep(120000);
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (serverOn) {
                    System.out.println("Verifying...");
                    executor.execute(()->{
                        //List<VerificareEntity> verificari = new ArrayList<>();
                        if(serverOn) {
                            boolean ok = true;
                            List<SpectacoleEntity> spectacole = repoSpectacole.findSpectacole();
                            for (SpectacoleEntity s : spectacole) {
                                VerificareEntity verif = new VerificareEntity();
                                verif.setSpectacol(s);
                                verif.setDateTime(OffsetDateTime.now());
                                verif.setSold(soldPerSpectacle(s));
                                verif.setSalesList(salesList(s));
                                if (!verifyPlaces(s) || !verifySold(s)) {
                                    System.out.println("Integrity error for " + s.getTitlu());
                                    ok = false;
                                    verif.setStatus("incorect");
                                } else {
                                    verif.setStatus("corect");
                                }
                                repoVerificare.save(verif);
                            }
                            if (ok) {
                                System.out.println("All ok");
                            } else {
                                System.out.println("Not ok");
                            }
                        }
                    });
                }else {
                    System.out.println("Done veryfing");
                }
            }
        });
    }

    public synchronized boolean verifySold(SpectacoleEntity spectacol){
        if(serverOn){
            Map<Long, Long> soldPerTranz = new HashMap<>();
            List<VanzareEntity> vanzari = repoVanzare.findVanzariBySpectacol(spectacol);
            for(VanzareEntity v:vanzari){
                if(soldPerTranz.containsKey(v.getIdTranzactie())){
                    Long price = soldPerTranz.get(v.getIdTranzactie());
                    price = price + spectacol.getPretBilet();
                    soldPerTranz.replace(v.getIdTranzactie(), price);
                }else {
                    soldPerTranz.put(v.getIdTranzactie(), spectacol.getPretBilet());
                }
            }
            Long sold = (long)0.0;
            for(Long key:soldPerTranz.keySet()){
                sold = sold + soldPerTranz.get(key);
            }
            if(!sold.equals(soldPerSpectacle(spectacol))){
                return false;
            }
            return true;
        }
        return false;
    }

    public synchronized boolean verifyPlaces(SpectacoleEntity spectacol){
        if(serverOn){
            List<VanzareEntity> vanzari = repoVanzare.findVanzariBySpectacol(spectacol);
            List<VanzareLocEntity> soldPlaces = new ArrayList<>();
            for(VanzareEntity v:vanzari){
                List<VanzareLocEntity> locuri = repoVanzareLoc.findLocByVanzare(v);
                if(locuri.size() > 1){
                    System.out.println("S-au vandut doua locuri in aceeasi tranzactie");
                    return false;
                }
                soldPlaces.add(locuri.get(0));
            }
            for(VanzareLocEntity p:soldPlaces){
                int count = 0;
                for(VanzareLocEntity q:soldPlaces){
                    if(q.equals(p)){
                        count++;
                    }
                }
                if(count != 1){
                    System.out.println("S-a vandut acelasi loc de doua ori pentru spectacolul " + spectacol.getTitlu());
                    return false;
                }
            }
            return true;
        }else {
            System.out.println("Server is off");
        }
        return false;
    }

    public synchronized Long soldPerSpectacle(SpectacoleEntity spectacol){
        Long price = spectacol.getPretBilet();
        List<VanzareEntity> vanzari = repoVanzare.findVanzariBySpectacol(spectacol);
        return price * vanzari.size();
    }

    public synchronized String salesList(SpectacoleEntity spectacol){
        StringBuilder out = new StringBuilder();
        for(VanzareEntity v: repoVanzare.findVanzariBySpectacol(spectacol)){
            out.append(v.getId().toString()).append(";");
        }
        return out.toString();
    }

    public synchronized List<VanzareLocEntity> getLocBySpectacol(SpectacoleEntity spectacol){
        List<VanzareLocEntity> locuri = new ArrayList<>();
        List<VanzareEntity> vanzari = repoVanzare.findVanzariBySpectacol(spectacol);
        for(VanzareEntity v:vanzari){
            locuri.add(repoVanzareLoc.findLocByVanzare(v).get(0));
        }
        return locuri;
    }

    public synchronized boolean isFree(Integer loc, SpectacoleEntity spectacol){
        boolean ok = true;
        for(VanzareLocEntity locVandut:getLocBySpectacol(spectacol)){
            if(locVandut.getNrLoc().equals((long)loc)){
                ok = false;
            }
        }
        return ok;
    }

    @Override
    public synchronized String buyTickets(List<Integer> locuri, SpectacoleEntity spetacol) throws RemoteException {
        String message = "";
        VanzareEntity last = repoVanzare.findLastTransaction();
        Long idTr = (long)0.0;
        if(last != null){
            idTr = last.getIdTranzactie() + 1;
        }

        boolean allAreFree = true;
        for(Integer loc:locuri){
            if(!isFree(loc, spetacol)){
                allAreFree = false;
            }
        }
        if(allAreFree) {
            for (Integer loc : locuri) {
                VanzareEntity vanzareEntity = new VanzareEntity();
                vanzareEntity.setIdTranzactie(idTr);
                vanzareEntity.setSpectacol(spetacol);
                vanzareEntity.setDataVanzare(OffsetDateTime.now());

                VanzareLocEntity vanzareLocEntity = new VanzareLocEntity();
                vanzareLocEntity.setVanzare(vanzareEntity);
                vanzareLocEntity.setNrLoc((long) loc);

                VanzareEntity outVanzare = repoVanzare.save(vanzareEntity);
                VanzareLocEntity outVanzareLoc = repoVanzareLoc.save(vanzareLocEntity);
                if (outVanzare == null || outVanzareLoc == null) {
                    message = "Something went wrong";
                } else {
                    message = "Tickets bought!";
                }

            }
        }else {
            message = "Sorry, some places were already bought";
        }

        return message;
    }

    @Override
    public List<SpectacoleEntity> getSpectacole() throws RemoteException {
        return repoSpectacole.findSpectacole();
    }
}
