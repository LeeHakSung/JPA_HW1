package com.alvin.jpastudy.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Alvin (playgo@coupang.com)
 * Date: 2015. 10. 23.
 * Time: 오후 12:25
 */
@Entity
@Table(name="TEAM")
public class Team {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="TEAM_ID")
    private int id;

    private String name;

    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<Member>();

    @ManyToOne
    @JoinColumn(name = "GROUP_ID", referencedColumnName = "GROUP_ID")
    private Group group;

    public Team(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return String.format("Team[id=%s, name='%s'']", id, name);
    }
}