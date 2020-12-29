package spectacole.repo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import spectacole.model.VanzareEntity;
import spectacole.model.VanzareLocEntity;

import java.util.ArrayList;
import java.util.List;

public class RepoVanzareLoc implements IRepoVanzareLoc {

    HibernateUtils utils;
    public RepoVanzareLoc(HibernateUtils utils){
        this.utils = utils;
    }

    @Override
    public VanzareLocEntity save(VanzareLocEntity vanzareLoc) {
        SessionFactory sessionFactory = utils.getInstance();
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            VanzareLocEntity vanzareLocEntity = (VanzareLocEntity)session.save(vanzareLoc);
            //System.out.println(vanzareLocEntity.getVanzare().getId());
            session.getTransaction().commit();
            return vanzareLocEntity;
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public List<VanzareLocEntity> findLocByVanzare(VanzareEntity vanzareEntity) {
        List<VanzareLocEntity> rez = new ArrayList<>();
        SessionFactory sessionFactory = utils.getInstance();
        try(Session session = sessionFactory.openSession()) {
            session.getTransaction();
            Query query = session.createQuery("from  VanzareLocEntity WHERE vanzare = :myVanzare");
            query.setParameter("myVanzare", vanzareEntity);
            List result = query.list();
            rez = (List<VanzareLocEntity>)result;
        }
        return rez;
    }

    @Override
    public int size() {
        SessionFactory sessionFactory = utils.getInstance();
        Integer nr = 0;
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            Query query = session.createQuery("select count (*) from VanzareLocEntity");
            Long count = (Long) query.uniqueResult();
            System.out.println("Rez=" + count);
            nr = Integer.parseInt(count.toString());
            session.getTransaction().commit();
        }
        return nr;
    }
}
