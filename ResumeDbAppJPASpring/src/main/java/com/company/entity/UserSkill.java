/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 *
 * @author HP
 */
@Entity
@Table(name = "user_skill")
@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "UserSkill.findAll", query = "SELECT u FROM UserSkill u"),
//    @NamedQuery(name = "UserSkill.findById", query = "SELECT u FROM UserSkill u WHERE u.id = :id"),
//    @NamedQuery(name = "UserSkill.findByUserId", query = "SELECT u FROM UserSkill u WHERE u.userId = :userId"),
//    @NamedQuery(name = "UserSkill.findBySkillId", query = "SELECT u FROM UserSkill u WHERE u.skillId = :skillId"),
//    @NamedQuery(name = "UserSkill.findByPower", query = "SELECT u FROM UserSkill u WHERE u.power = :power")})
public class UserSkill implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "user_id")
    private int user;
    @Basic(optional = false)
    @Column(name = "skill_id")
    private int skill;
    @Basic(optional = false)
    @Column(name = "power")
    private int power;

    public UserSkill() {
    }

    public UserSkill(Integer id) {
        this.id = id;
    }

    public UserSkill(Integer id, int user, int skill, int power) {
        this.id = id;
        this.user = user;
        this.skill = skill;
        this.power = power;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getSkill() {
        return skill;
    }

    public void setSkill(int skill) {
        this.skill = skill;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserSkill)) {
            return false;
        }
        UserSkill other = (UserSkill) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.company.entity.UserSkill[ id=" + id + " ]";
    }
    
}
