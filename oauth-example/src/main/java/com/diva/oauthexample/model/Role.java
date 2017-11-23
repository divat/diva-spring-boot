package com.diva.oauthexample.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="mast_role")
public class Role {
	
	@Id
	@SequenceGenerator(name="roleid",sequenceName="mast_role_role_id_seq")
	@GeneratedValue(generator="roleid")
	@Column(name="role_id")
	private Integer id;
	
	@Column(name="role_name")
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setNome(String name) {
		this.name = name;
	}
}