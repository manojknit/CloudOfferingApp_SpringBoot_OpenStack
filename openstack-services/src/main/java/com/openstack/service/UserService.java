package com.openstack.service;


import org.springframework.data.jpa.repository.JpaRepository;

import com.openstack.model.OpenStackUser;

public interface UserService extends JpaRepository<OpenStackUser, Long> {


}
