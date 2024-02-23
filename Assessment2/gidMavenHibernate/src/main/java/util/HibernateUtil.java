package util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
	
	// Create a sessionFactory attribute
	public static final SessionFactory sessionFactory = buildSessionFactory();
	
	// Method to build SessionFactory
	public static SessionFactory buildSessionFactory() {
		
		try {
			
			// Return the SessionFactory Object
			StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
			Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();
			return metadata.getSessionFactoryBuilder().build();
			
			
		} catch (Exception e) {
            e.printStackTrace();
            
            throw new ExceptionInInitializerError(e);
        }
		
	}

}
