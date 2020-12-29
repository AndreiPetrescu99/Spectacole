package spectacole.repo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import spectacole.model.VerificareEntity;

public class RepoVerificare implements IRepoVerificare{

    HibernateUtils utils;
    public RepoVerificare(HibernateUtils utils){
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
            //System.out.println("Rez=" + count);
            nr = Integer.parseInt(count.toString());
            session.getTransaction().commit();
        }
        return nr;
    }

    @Override
    public VerificareEntity save(VerificareEntity verificare) {
        SessionFactory sessionFactory = utils.getInstance();
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(verificare);
            session.getTransaction().commit();
            return verificare;
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }
}
