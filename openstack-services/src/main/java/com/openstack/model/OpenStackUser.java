package com.openstack.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;



@Entity
public class OpenStackUser {
	
	@Id
    @GeneratedValue
    private Long id;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(unique = true)
    private String username;
    private String password;
    
    @OneToMany(mappedBy="user")
    private Set<OpenStackInstance> instances = new HashSet<OpenStackInstance>();
    
    public Set<OpenStackInstance> getInstances() {
		return instances;
	}
	public void setInstances(Set<OpenStackInstance> instances) {
		this.instances = instances;
	}
	public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}