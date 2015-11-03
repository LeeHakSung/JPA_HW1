package com.alvin.jpastudy.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

/**
 * User: Alvin (playgo@coupang.com)
 * Date: 2015. 10. 23.
 * Time: 오후 12:25
 */
@Entity
@Table(name="GROUP")
public class Group {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="GROUP_ID")
    private int id;

    private String name;

    @OneToMany(mappedBy = "group")
    private List<Team> teams = new ArrayList<Team>();

    public Group(String name) {
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

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    @Override
    public String toString() {
        return String.format("Group[id=%s, name='%s'']", id, name);
    }
}