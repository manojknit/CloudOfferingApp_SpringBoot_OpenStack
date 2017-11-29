package com.openstack.service;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.openstack4j.api.Builders;
import org.openstack4j.api.OSClient.OSClientV3;
import org.openstack4j.model.compute.Action;
import org.openstack4j.model.compute.Flavor;
import org.openstack4j.model.compute.Server;
import org.openstack4j.model.compute.ServerCreate;
import org.openstack4j.model.image.Image;
import org.openstack4j.model.network.Network;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openstack.util.Utility;


@Service
public class OpenStackIntanceServiceImpl {
	
	@Autowired
	private OpenStackAuthenticate openStackAuthenticate;
	
	public static final Logger logger=LoggerFactory.getLogger(OpenStackIntanceServiceImpl.class);
	
	public String createInstance(String instanceName,String flavor,String image,String network) {
		OSClientV3 os = openStackAuthenticate.authenticate();
		List<? extends Network> networksAll = os.networking().network().list();
		List<? extends Flavor> flavors = os.compute().flavors().list();
		List<? extends Image> images = os.images().list();
		List<String> networkIds = new ArrayList<>();
		String imageId="";
		String flavorId="";
		if (network.isEmpty()) {
			for (Network network1 : networksAll) {
				networkIds.add(network1.getId());
			}
		} else {
			for (Network network1 : networksAll) {
				if (network1.getName().equals(network)){
					networkIds.add(network1.getId());
				}
			}
		}
		
		for (Image image1 : images) {
			imageId=Utility.getResourceIdbyName(image1, image);
			if (!imageId.equals("-9999")) {
				break;
			}
		}
		
	
		for (Flavor flavor1 : flavors) {
			flavorId=Utility.getFlavorIdbyName(flavor1, flavor);
			if (!flavorId.equals("-9999")) {
				break;
			}
		}

		// Create a Server Model Object
		//ServerCreate sc = Builders.server().name("test 2").flavor("61465bf0-b4e2-48d3-9ad2-498a911fc3d3").image("9c91414d-1f05-470c-ac5e-b2a99d91931b").build();
		ServerCreate sc = Builders.server().name(instanceName).flavor(flavorId).image(imageId).networks(networkIds).build();
		// Boot the Server
		Server server = openStackAuthenticate.authenticate().compute().servers().boot(sc);
		logger.info("Server Created "+server);
		return server.getId();
	}
	
	public String stopInstance(String instanceId) {
		OSClientV3 os = openStackAuthenticate.authenticate();
		os.compute().servers().action(instanceId, Action.STOP);
		return "S";
	}
	
	public String deleteInstance(String instanceId) {
		OSClientV3 os = openStackAuthenticate.authenticate();
		os.compute().servers().delete(instanceId);
		return "S";
	}

}
