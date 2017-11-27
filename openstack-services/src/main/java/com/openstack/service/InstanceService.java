package com.openstack.service;


import org.springframework.data.jpa.repository.JpaRepository;

import com.openstack.model.OpenStackInstance;

public interface InstanceService extends JpaRepository<OpenStackInstance, Long> {


}
