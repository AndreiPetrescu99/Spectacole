package spectacole.repo;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtils {
    private SessionFactory sessionFactory;

    public void initialize() {

        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            System.out.println("Exceptie "+e);
            StandardServiceRegistryBuilder.destroy( registry );
        }
    }

    public void close() {
        if ( sessionFactory != null ) {
            sessionFactory.close();
            //sessionFactory = null;
        }
    }

    public SessionFactory getInstance(){
        if(sessionFactory == null)
            initialize();
        return sessionFactory;
    }

}
