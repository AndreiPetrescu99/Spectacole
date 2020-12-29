package spectacole.repo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import spectacole.model.SpectacoleEntity;
import spectacole.model.VanzareEntity;

import java.util.ArrayList;
import java.util.List;

public class RepoSpectacole implements IRepoSpectacole {

    HibernateUtils utils;
    public RepoSpectacole(HibernateUtils utils){
        this.utils = utils;
    }

    @Override
    public int size() {
        SessionFactory sessionFactory = utils.getInstance();
        Integer nr = 0;
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            Query query = session.createQuery("select count (*) from SpectacoleEntity");
            Long count = (Long) query.uniqueResult();
            System.out.println("Rez=" + count);
            nr = Integer.parseInt(count.toString());
            session.getTransaction().commit();
        }
        return nr;
    }

    @Override
    public SpectacoleEntity save(SpectacoleEntity spectacol) {
        SessionFactory sessionFactory = utils.getInstance();
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(spectacol);
            session.getTransaction().commit();
            return spectacol;
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public List<SpectacoleEntity> findSpectacole() {
        List<SpectacoleEntity> rez = new ArrayList<>();
        SessionFactory sessionFactory = utils.getInstance();
        try(Session session = sessionFactory.openSession()) {
            session.getTransaction();
            Query query = session.createQuery("from SpectacoleEntity");
            List result = query.list();
            rez = (List<SpectacoleEntity>)result;
        }
        return rez;
    }
}
