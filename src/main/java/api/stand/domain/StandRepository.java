package api.stand.domain;

import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class StandRepository {

    private static final String PERSISTENCE_UNIT_NAME = "Stand";

    /**
     * EntityManagerFactory instance is to support instantiation
     * of EntityManager instances. Never null.
     */
    private static EntityManagerFactory factory =
            Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

    /** The Jpa Entity Manager. Never null.
     */
    private EntityManager entityManager;

    /** Constructor, builds a stand repository.
     */
    public StandRepository() {
        entityManager = factory.createEntityManager();
    }

    /** Finds the Stand in this repository for the specified id.
     *
     * @param id the Stand's id. It MUST BE the beacons' unique mac address.
     * It cannot be null.
     *
     * @return the Stand according to id, never null.
     * Throws NullPointerException if given id is null.
     */
    public Stand findBy(final String id) {
        Validate.notNull(id, "The id cannot be null.");
        beginTransaction();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Stand> criteria;
        criteria = builder.createQuery(Stand.class);
        Root<Stand> root = criteria.from(Stand.class);
        criteria.where(builder.equal(root.get("id"), id));
        criteria.select(root);

        final Stand stand =
                entityManager.createQuery(criteria).getSingleResult();

        commitTransaction();
        return stand;
    }

    /** Creates EntityManager's instance and begins its transaction.
     *
     * TODO (ma 2019-12-11) convert services into @Transaction ones.
     * TODO delete this method then.
     */
    private void beginTransaction() {
        entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();
    }

    /** Commits EntityManager's transaction and closes it.
     *
     * TODO (ma 2019-12-11) convert services into @Transaction ones.
     * TODO delete this method then.
     */
    private void commitTransaction() {
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    /** Finds all the Stands in this repository for the specified ids.
     *
     * @param ids Stands' ids.
     * @return A Stand's list. Throws NullPointerException or IllegalArgumentException
     * if ids is null or empty.
     */
    public List<Stand> findBy(List<String> ids) {
        Validate.notEmpty(ids, "The Stands' ids cannot be empty");
        beginTransaction();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Stand> criteria;
        criteria = builder.createQuery(Stand.class);
        Root<Stand> root = criteria.from(Stand.class);
        criteria.select(root)
                .where(root.get("id")
                        .in(ids));

        final List<Stand> stands =
                entityManager.createQuery(criteria).getResultList();

        commitTransaction();
        return stands;
    }

    /** Finds all the Stands in this repository ordered by ranking.
     *
     * @return A Stand's list ordered by ranking.
     */
    public List<Stand> findOrderedByRanking() {
        beginTransaction();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Stand> criteria;
        criteria = builder.createQuery(Stand.class);
        Root<Stand> root = criteria.from(Stand.class);
        criteria.orderBy(builder.desc(root.get("ranking")));
        criteria.select(root);

        final List<Stand> stands =
                entityManager.createQuery(criteria).getResultList();

        commitTransaction();

        return stands;
    }
}

