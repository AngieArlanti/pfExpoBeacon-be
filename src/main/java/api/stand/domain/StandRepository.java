package api.stand.domain;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Repository;

@Repository
public class StandRepository {

    private static final String PERSISTENCE_UNIT_NAME = "Stand";
    private static EntityManagerFactory factory =
      Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

    /** The Jpa Entity Manager, never null.
     */
    //@PersistenceContext
    //private EntityManager entityManager;

    /** Constructor, builds a stand repository.
     *
     * //@param theEntityManager the entity manager to use. It cannot be null.
     */
    public StandRepository(/*final EntityManager theEntityManager*/) {
        /*Validate.notNull(theEntityManager, "The entity manager cannot be " +
          "null.");
        entityManager = theEntityManager;*/
    }

    /** Finds the Stand in this repository for the specified macAddress.
     *
     * @param macAddress the Stand's macAddress. It cannot be null.
     *
     * @return the Stand according to macAddress, never null.
     */
    public Stand findByMacAddress(final String macAddress) {
        Validate.notNull(macAddress, "The macAddress cannot be null.");
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Stand> criteria;
        criteria = builder.createQuery(Stand.class);
        Root<Stand> root = criteria.from(Stand.class);
        criteria.where(builder.equal(root.get("macAddress"), macAddress));
        criteria.select(root);

        final Stand stand =
          entityManager.createQuery(criteria).getSingleResult();

        entityManager.getTransaction().commit();
        entityManager.close();

        return stand;
    }
}

