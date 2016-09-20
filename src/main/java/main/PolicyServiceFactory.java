package main;

import java.net.URI;


import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


import org.apache.olingo.odata2.jpa.processor.api.ODataJPAContext;
import org.apache.olingo.odata2.jpa.processor.api.ODataJPAServiceFactory;
import org.apache.olingo.odata2.jpa.processor.api.exception.ODataJPARuntimeException;




public class PolicyServiceFactory extends ODataJPAServiceFactory {


    private static final String PERSISTENCE_UNIT_NAME = "mysql";
    @Override
    public ODataJPAContext initializeODataJPAContext() throws ODataJPARuntimeException {
        ODataJPAContext oDataJPAContext = this.getODataJPAContext();
        try {
            URI uri = oDataJPAContext.getODataContext().getPathInfo().getRequestUri();
            uri.getQuery();
            EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
            oDataJPAContext.setEntityManagerFactory(emf);
            oDataJPAContext.setPersistenceUnitName(PERSISTENCE_UNIT_NAME);
            return oDataJPAContext;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}