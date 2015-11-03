package com.alvin.jpastudy.entity;

import javax.persistence.*;

/**
 * User: Alvin (playgo@coupang.com)
 * Date: 2015. 10. 23.
 * Time: 오후 12:25
 */
@Entity
@Table(name="MEMBER")
public class Member {
    @Id
    @Column(name="ID")
    private String id;

    @Column(name="NAME")
    private String name;

    @Column(name="AGE")
    private int age;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID", referencedColumnName = "TEAM_ID")
    private Team team;

    public Member() {}

    public Member(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Member(String id, String name, int age, Team team) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.team = team;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return String.format("Member[id=%s, name='%s', age='%s']", id, name, age);
    }

}