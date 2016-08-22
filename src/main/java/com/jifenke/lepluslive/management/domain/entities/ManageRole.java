package com.jifenke.lepluslive.management.domain.entities;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * Created by Administrator on 2016/7/26.
 */
@Entity
@Table(name = "MANAGEROLE")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ManageRole {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String roleName;

  public String getRoleName() {
    return roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
