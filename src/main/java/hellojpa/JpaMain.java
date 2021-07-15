package hellojpa;

import org.hibernate.Hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Member member = new Member();
            member.setUsername("member1");
            member.setHomeAddress(new Address("homeCity", "street", "111111"));

            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("피자");
            member.getFavoriteFoods().add("족발");

            member.getAddressesHistory().add(new AddressEntity("old1", "street", "111111"));
            member.getAddressesHistory().add(new AddressEntity("old2", "street", "111111"));

            em.persist(member);

            em.flush();
            em.clear();

            System.out.println("================== Start =====================");
            Member findMember = em.find(Member.class, member.getId());

//            findMember.getHomeAddress().setCity("newcity");

//            Address a = findMember.getHomeAddress();
//            findMember.setHomeAddress(new Address("newCity", a.getStreet(), a.getZipcode()));

//            치킨 -> 한식
//            findMember.getFavoriteFoods().remove("치킨");
//            findMember.getFavoriteFoods().add("한식");

//            System.out.println("================== Address =====================");
//            findMember.getAddressesHistory().remove(new AddressEntity("old1", "street", "111111"));
//            findMember.getAddressesHistory().add(new AddressEntity("newCity1", "street", "111111"));

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

        emf.close();

    }

}
