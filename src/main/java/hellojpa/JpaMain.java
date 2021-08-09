package hellojpa;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            /*
//            JPQL
            List<Member> result = em.createQuery(
                    "select m From Member m where m.username like '%kim%'",
                    Member.class
            ).getResultList();
            */

            /*
//            Criteria 사용준비
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Member> query = cb.createQuery(Member.class);

            Root<Member> m = query.from(Member.class);

            CriteriaQuery<Member> cbQuery = query.select(m);
            String username = "aaa";
            if (username != null) {
                cbQuery = cbQuery.where(cb.equal(m.get("username"), "kim"));
            }

            List<Member> resultList = em.createQuery(cbQuery).getResultList();
            */

            Member member = new Member();
            member.setUsername("member1");
            em.persist(member);

//            flush -> commit, query

            em.flush();

//            결과 0
//            DBconn.executeQuery("select * from member");

//            Native SQL
            List resultList = em.createNativeQuery("select MEMBER_ID, city, street, zipcode, USERNAME from MEMBER ").getResultList();

            resultList.forEach((s) -> {
                System.out.println("Member : " + s.toString());
            });

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
