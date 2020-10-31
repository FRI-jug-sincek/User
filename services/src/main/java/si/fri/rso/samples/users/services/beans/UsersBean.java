package si.fri.rso.samples.users.services.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;

import si.fri.rso.samples.users.lib.User;
import si.fri.rso.samples.users.models.converters.UserConverter;
import si.fri.rso.samples.users.models.entities.UserEntity;


@RequestScoped
public class UsersBean {

    private Logger log = Logger.getLogger(UsersBean.class.getName());

    @Inject
    private EntityManager em;

    public List<User> getUsers() {

        TypedQuery<UserEntity> query = em.createNamedQuery(
                "UserEntity.getAll", UserEntity.class);

        List<UserEntity> resultList = query.getResultList();

        return resultList.stream().map(UserConverter::toDto).collect(Collectors.toList());
    }

    public List<User> getUsersFilter(UriInfo uriInfo) {

        QueryParameters queryParameters = QueryParameters.query(uriInfo.getRequestUri().getQuery()).defaultOffset(0)
                .build();

        return JPAUtils.queryEntities(em, UserEntity.class, queryParameters).stream()
                .map(UserConverter::toDto).collect(Collectors.toList());
    }

    public User getUser(Integer id) {

        UserEntity userEntity = em.find(UserEntity.class, id);

        if (userEntity == null) {
            throw new NotFoundException();
        }

        User user = UserConverter.toDto(userEntity);

        return user;
    }

    public User createUser(User user) {

        UserEntity userEntity = UserConverter.toEntity(user);

        try {
            beginTx();
            em.persist(userEntity);
            commitTx();
        }
        catch (Exception e) {
            rollbackTx();
        }

        if (userEntity.getId() == null) {
            throw new RuntimeException("Entity was not persisted");
        }

        return UserConverter.toDto(userEntity);
    }

    public User putUser(Integer id, User user) {

        UserEntity c = em.find(UserEntity.class, id);

        if (c == null) {
            return null;
        }

        UserEntity updatedUserEntity = UserConverter.toEntity(user);

        try {
            beginTx();
            updatedUserEntity.setId(c.getId());
            updatedUserEntity = em.merge(updatedUserEntity);
            commitTx();
        }
        catch (Exception e) {
            rollbackTx();
        }

        return UserConverter.toDto(updatedUserEntity);
    }

    public boolean deleteUser(Integer id) {

        UserEntity user = em.find(UserEntity.class, id);

        if (user != null) {
            try {
                beginTx();
                em.remove(user);
                commitTx();
            }
            catch (Exception e) {
                rollbackTx();
            }
        }
        else {
            return false;
        }

        return true;
    }

    private void beginTx() {
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
    }

    private void commitTx() {
        if (em.getTransaction().isActive()) {
            em.getTransaction().commit();
        }
    }

    private void rollbackTx() {
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
    }
}
