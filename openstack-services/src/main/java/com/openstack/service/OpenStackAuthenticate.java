package com.openstack.service;

import org.openstack4j.api.OSClient.OSClientV3;
import org.openstack4j.model.common.Identifier;
import org.openstack4j.openstack.OSFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class OpenStackAuthenticate {
	
	public static final Logger logger = LoggerFactory.getLogger(OpenStackAuthenticate.class);
	
	@Value("${openstack.user-name}")
	private String userName;

	@Value("${openstack.password}")
	private String password;
	
	@Value("${openstack.host-url}")
	private String openStackHost;
	
	@Bean
	public OSClientV3 authenticate() {
		logger.info("authenticate");
		//use Identifier.byId("domainId") or Identifier.byName("example-domain")
		Identifier domainIdentifier = Identifier.byId("default");

		//unscoped authentication
		//as the username is not unique across domains you need to provide the domainIdentifier
		OSClientV3 os = OSFactory.builderV3()
								.endpoint(openStackHost+":5000/v3")
		                        .credentials(userName,password, domainIdentifier)
		                       .authenticate();
		logger.info("Open Stack Authentication Successful!!");
		return os;
	}

}
