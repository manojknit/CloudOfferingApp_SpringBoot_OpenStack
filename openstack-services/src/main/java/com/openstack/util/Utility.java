package com.openstack.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openstack4j.model.common.BasicResource;
import org.openstack4j.model.common.Resource;
import org.openstack4j.model.compute.Flavor;

public class Utility {
	
	public static String getResourceIdbyName(BasicResource resource,String name) {
		String resourceId="-9999";
		if (resource.getName().equals(name)){
			resourceId = resource.getId();
		}
		return resourceId;
	}

	public static String getFlavorIdbyName(Flavor resource,String name) {
		String resourceId="-9999";
		if (resource.getName().equals(name)){
			resourceId = resource.getId();
		}
		return resourceId;
	}
	
	public static String formattedSysDate() {
		Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");
        return sdf.format(date);
	}
}
