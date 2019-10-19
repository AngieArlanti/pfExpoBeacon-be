package api.stand.domain;

import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class StandRepository {

    private static final String PERSISTENCE_UNIT_NAME = "Stand";
    private static EntityManagerFactory factory =
            Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

    /** The Jpa Entity Manager, never null.
     */
    private EntityManager entityManager;

    /** Constructor, builds a stand repository.
     */
    public StandRepository() {
        entityManager = factory.createEntityManager();
    }

    /** Finds the Stand in this repository for the specified macAddress.
     *
     * @param macAddress the Stand's macAddress. It cannot be null.
     *
     * @return the Stand according to macAddress, never null.
     */
    public Stand findByMacAddress(final String macAddress) {
        Validate.notNull(macAddress, "The macAddress cannot be null.");

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

