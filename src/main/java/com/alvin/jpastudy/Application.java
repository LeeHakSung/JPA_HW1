package com.alvin.jpastudy;

import com.alvin.jpastudy.entity.Group;
import com.alvin.jpastudy.entity.Member;
import com.alvin.jpastudy.entity.Team;
import com.alvin.jpastudy.repository.MemberRepository;
import com.alvin.jpastudy.service.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Alvin (playgo@coupang.com)
 * Date: 2015. 10. 23.
 * Time: 오후 7:32
 */
@SpringBootApplication
public class Application implements CommandLineRunner {
    @Autowired
    private RepositoryService repositoryService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
        System.out.print("Application Started!!");
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("run() Started!!");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager();

        //Transaction
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            logic(em);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }

    private void logic(EntityManager em) {
        System.out.println("logic() Started!!");

        createInstance();
        crudMembers(em);
        crudTeams(em);
        crudGroups(em);

        System.out.println("logic() Ended!!");
    }

    private void printMember(EntityManager em, List<Member> members, String action) {
        System.out.println();
        System.out.println("<" + action + ">");
        System.out.println("-------------------------------");

        for (int i = 0; i < members.size(); i++) {
            Member member = em.find(Member.class, "id" + (i + 1));
            System.out.println(member);
        }

        System.out.println();
    }

    private Group cto;
    private List<Team> ctoTeams;
    private Team bali;
    private Team greenLand;
    private List<Member> baliMembers;
    private List<Member> greenLandMembers;

    private void createInstance() {
//        cto = new Group("CTO");
        bali = new Team("Bali");
        greenLand = new Team("Greenland");

//        ctoTeams = new ArrayList<Team>();
//
//        ctoTeams.add(bali);
//        ctoTeams.add(greenLand);
//        cto.setTeams(ctoTeams);

        bali.setGroup(cto);
        greenLand.setGroup(cto);

        baliMembers = new ArrayList<Member>();

        baliMembers.add(new Member("id1", "Alvin", 35, bali));
        baliMembers.add(new Member("id2", "Lapin", 28, bali));
        baliMembers.add(new Member("id3", "Hover", 27, bali));
        baliMembers.add(new Member("id4", "Dooboo", 27, bali));
        baliMembers.add(new Member("id5", "Amy", 24, bali));

        greenLandMembers = new ArrayList<Member>();

        greenLandMembers.add(new Member("id6", "A", 35, greenLand));
        greenLandMembers.add(new Member("id7", "B", 28, greenLand));
        greenLandMembers.add(new Member("id8", "C", 27, greenLand));
        greenLandMembers.add(new Member("id9", "D", 27, greenLand));
        greenLandMembers.add(new Member("id10", "E", 24, greenLand));
    }

    private void crudMember(EntityManager em, Team team, List<Member> members, String id) {
        System.out.println("----- <crudMember> -----");

        // Create
        team.getMembers().addAll(members);
        em.persist(team);

        for (Member member : members) {
            em.persist(member);
        }

        // Read
        printMember(em, members, "Read");

        // Update
        Member member = em.find(Member.class, id);
        member.setName(member.getName() + "_Updated");
        em.persist(member);

        printMember(em, members, "Update");

        // Delete
        em.remove(member);
        members.remove(4);
        printMember(em, members, "Delete");
    }

    private void crudMembers(EntityManager em) {
        crudMember(em, bali, baliMembers, baliMembers.get(baliMembers.size() - 1).getId());
        crudMember(em, greenLand, greenLandMembers, greenLandMembers.get(greenLandMembers.size() - 1).getId());
    }

    private void crudGroup(EntityManager em, int id) {
        System.out.println("----- <crudTeam> -----");

        // Create
        Group group = em.find(Group.class, id);
        System.out.println(group);

        for (Team team : group.getTeams()) {
            System.out.println(team);
        }

        // Read
        System.out.println(group);

        // Update
        group.setName(group.getName() + "_Best");
        em.persist(group);
        System.out.println(group);
    }

    private void crudTeam(EntityManager em, int id) {
        System.out.println("----- <crudTeam> -----");

        // Create
        Team team = em.find(Team.class, id);
        System.out.println(team);

        for (Member member : team.getMembers()) {
            System.out.println(member);
        }

        // Read
        System.out.println(team);

        // Update
        team.setName(team.getName() + "_Best");
        em.persist(team);
        System.out.println(team);
    }

    private void crudTeams(EntityManager em) {
        System.out.println("----- <crudTeams> -----");

        crudTeam(em, bali.getId());
        crudTeam(em, greenLand.getId());
    }

    private void crudGroups(EntityManager em) {
        System.out.println("----- <crudGroups> -----");

        //crudGroup(em, cto.getId());
    }
}
