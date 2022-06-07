package mpp.project.core.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class CustomRepositorySupport {
    @PersistenceContext
    private EntityManager entityManager;
}
