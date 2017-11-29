package com.openstack.service;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.openstack.model.OpenStackInstance;

public interface InstanceService extends JpaRepository<OpenStackInstance, Long> {

	@Query("SELECT coalesce(max(osi.id), 0) FROM OpenStackInstance osi")
	Long getMaxId();
}
