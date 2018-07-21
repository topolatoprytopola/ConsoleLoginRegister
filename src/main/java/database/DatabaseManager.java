package database;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class DatabaseManager {
    private EntityManager entityManager;
    private EntityManagerFactory emf;
    public DatabaseManager()
    {
        emf = Persistence.createEntityManagerFactory("logger");
        entityManager = emf.createEntityManager();
    }
    public EntityUser getUserbyName(String name)
    {
        List<EntityUser> users  = this.getUsers();
        for(EntityUser entityUser:users)
        {
            if(entityUser.getName().equals(name))
            {
                return entityUser;
            }
        }
        return null;
    }
    public void addUser(String name, String password, String email, String phoneNumber)
    {
        EntityUser User = new EntityUser(name,password,email,phoneNumber);
        entityManager.getTransaction().begin();
        entityManager.persist(User);
        entityManager.getTransaction().commit();
    }
    public void removeUser(int id)
    {
        EntityUser user = entityManager.find(EntityUser.class,id);
        entityManager.getTransaction().begin();
        entityManager.remove(user);
        entityManager.getTransaction().commit();
    }
    public void editPhoneNumber(EntityUser user, String PhoneNumber)
    {
        entityManager.getTransaction().begin();
        user.setPhonenumber(PhoneNumber);
        entityManager.getTransaction().commit();
    }
    public void editEmail(EntityUser user, String Email)
    {
        entityManager.getTransaction().begin();
        user.setEmail(Email);
        entityManager.getTransaction().commit();
    }
    public List<EntityUser> getUsers() {
        List<EntityUser> users = null;
        try {
            Query q = entityManager.createQuery("SELECT b FROM EntityUser b", EntityUser.class);
            users = q.getResultList();
        }
        catch(Exception e) {
            System.err.println("Blad przy pobieraniu rekordów: " + e);
        }
        return users;
    }
    public void close()
    {
        entityManager.close();
        emf.close();
    }
}
