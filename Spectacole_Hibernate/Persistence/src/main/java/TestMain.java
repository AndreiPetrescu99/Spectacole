import spectacole.model.SpectacoleEntity;
import spectacole.model.VanzareEntity;
import spectacole.model.VanzareLocEntity;
import spectacole.repo.HibernateUtils;
import spectacole.repo.RepoSpectacole;
import spectacole.repo.RepoVanzare;
import spectacole.repo.RepoVanzareLoc;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class TestMain {


    public static void main(String[] args){
        HibernateUtils hibernateUtils = new HibernateUtils();
        hibernateUtils.initialize();
        System.out.println("OK");
        RepoSpectacole repoSpectacole = new RepoSpectacole(hibernateUtils);
        RepoVanzare repoVanzare = new RepoVanzare(hibernateUtils);
        RepoVanzareLoc repoVanzareLoc = new RepoVanzareLoc(hibernateUtils);

        SpectacoleEntity spectacoleEntity = new SpectacoleEntity();
        spectacoleEntity.setId((long)1);
        spectacoleEntity.setTitlu("S1");
        spectacoleEntity.setPretBilet(new Long(123));
        spectacoleEntity.setDataSpectacol(OffsetDateTime.now());
        //SpectacoleEntity out = repoSpectacole.save(spectacoleEntity);


        SpectacoleEntity spectacoleEntity1 = new SpectacoleEntity();
        spectacoleEntity1.setId((long)2);
        spectacoleEntity1.setTitlu("S2");
        spectacoleEntity1.setPretBilet(new Long(156));
        spectacoleEntity1.setDataSpectacol(OffsetDateTime.now());
        //repoSpectacole.save(spectacoleEntity);


        SpectacoleEntity spectacoleEntity2 = new SpectacoleEntity();
        spectacoleEntity2.setId((long)3);
        spectacoleEntity2.setTitlu("S3");
        spectacoleEntity2.setPretBilet(new Long(176));
        spectacoleEntity2.setDataSpectacol(OffsetDateTime.now());
        //repoSpectacole.save(spectacoleEntity);



        System.out.println("Spectacole= " + repoSpectacole.size());



//        VanzareEntity vanzareEntity = new VanzareEntity();
//        vanzareEntity.setDataVanzare(OffsetDateTime.now());
//        vanzareEntity.setSpectacol(spectacoleEntity);
//        //repoVanzare.save(vanzareEntity);
//
//        System.out.println("Vanzari= " + repoVanzare.size());
//
//        VanzareLocEntity vanzareLocEntity = new VanzareLocEntity();
//        vanzareLocEntity.setNrLoc(Long.parseLong("1"));
//        vanzareLocEntity.setVanzare(vanzareEntity);
//        //repoVanzareLoc.save(vanzareLocEntity);
//
//        System.out.println("Vanzari Loc= " + repoVanzareLoc.size());
//
//
//        List<VanzareLocEntity> locuri = new ArrayList<>();
//        List<VanzareEntity> vanzari = repoVanzare.findVanzariBySpectacol(spectacoleEntity2);
//        for(VanzareEntity v:vanzari){
//            locuri.add(repoVanzareLoc.findLocByVanzare(v).get(0));
//        }


    }
}
