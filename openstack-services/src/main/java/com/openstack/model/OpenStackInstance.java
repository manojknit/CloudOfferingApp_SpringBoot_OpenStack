package com.openstack.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class OpenStackInstance {
    @Id
    @GeneratedValue
    private Long id;
    
    private String instanceId;
    private String instanceName;
    private String instanceURL;
    private String createdDate;
    private String terminatedDate;
    private String instanceType;
    private String status;
   
    @ManyToOne
   	@JoinColumn (name="user_id")
   	@JsonBackReference
	private OpenStackUser user;
    
    
    
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getInstanceName() {
		return instanceName;
	}
	public void setInstanceName(String instanceName) {
		this.instanceName = instanceName;
	}
	public OpenStackUser getUser() {
		return user;
	}
	public void setUser(OpenStackUser user) {
		this.user = user;
	}
	public String getInstanceId() {
		return instanceId;
	}
	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}
	public String getInstanceURL() {
		return instanceURL;
	}
	public void setInstanceURL(String instanceURL) {
		this.instanceURL = instanceURL;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getTerminatedDate() {
		return terminatedDate;
	}
	public void setTerminatedDate(String terminatedDate) {
		this.terminatedDate = terminatedDate;
	}
	public String getInstanceType() {
		return instanceType;
	}
	public void setInstanceType(String instanceType) {
		this.instanceType = instanceType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
