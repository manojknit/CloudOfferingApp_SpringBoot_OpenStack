package com.openstack.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.openstack.model.OpenStackInstance;
import com.openstack.model.OpenStackUser;
import com.openstack.service.InstanceService;
import com.openstack.service.UserService;
import com.openstack.util.CustomErrorType;
import com.openstack.util.CustomMessageType;


@RestController
@RequestMapping("/instances")
public class InstanceRestController {
    @Autowired
    private InstanceService instanceService;
    @Autowired
    private UserService userService;
  

    public static final Logger logger = LoggerFactory.getLogger(InstanceRestController.class);

  
    // -------------------Retrieve All Instances ---------------------------------------

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<OpenStackInstance>> listAllUsers() {
        List<OpenStackInstance> instances = (List<OpenStackInstance>) instanceService.findAll();
        if (instances.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<OpenStackInstance>>(instances, HttpStatus.OK);
    }

    // -------------------Retrieve All Instances by UserName ------------------------------------------

    @RequestMapping(value = "/{username:.+}", method = RequestMethod.GET)
    public ResponseEntity<?> listInstanceByUserName(@PathVariable("username") String username) {
        logger.info("Fetching OpenStackUser with username {}", username);
        OpenStackUser inputUser = new OpenStackUser();
        inputUser.setUsername(username);
        OpenStackUser user = userService.findOne(Example.<OpenStackUser>of(inputUser, ExampleMatcher.matching()
                .withMatcher("username", ExampleMatcher.GenericPropertyMatchers.ignoreCase())));
        OpenStackInstance inputInstance = new OpenStackInstance();
        inputInstance.setUser(user);
        List<OpenStackInstance> instances = instanceService.findAll(Example.<OpenStackInstance>of(inputInstance, ExampleMatcher.matching()
                .withMatcher("user", ExampleMatcher.GenericPropertyMatchers.ignoreCase())));
        if (instances == null) {
            logger.error("OpenStackInstance with username {} not found.", username);
            return new ResponseEntity(new CustomErrorType("Instances with UserName " + username
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<OpenStackInstance>>(instances, HttpStatus.OK);
    }
    

    // -------------------Create an OpenStackInstance -------------------------------------------

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody OpenStackInstance instance, UriComponentsBuilder ucBuilder) {
        logger.info("Creating OpenStackInstance : {}", instance);
        logger.info("openStackInstance User : {}", instance.getUser());
        instanceService.save(instance);
        return new ResponseEntity<OpenStackInstance>(instance, HttpStatus.CREATED);
    }

    // ------------------- Update an OpenStackInstance ------------------------------------------------

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateInstance(@PathVariable("id") Long id, @RequestBody OpenStackInstance instance) {
        logger.info("Updating OpenStackUser with id {}", id);

        OpenStackInstance currentInstance = instanceService.findOne(id);
        currentInstance.setStatus(instance.getStatus());
        currentInstance.setTerminatedDate(instance.getTerminatedDate());
        if (currentInstance == null) {
            logger.error("Unable to update. OpenStackInstance with id {} not found.", id);
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("Unable to upate. OpenStackInstance with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        instanceService.save(currentInstance);
        return new ResponseEntity<OpenStackInstance>(currentInstance, HttpStatus.OK);
    }

    // ------------------- Delete an OpenStackInstance ------------------------------------

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteFoodItem(@PathVariable("id") Long id) {
        logger.info("Fetching & Deleting OpenStackUser with id {}", id);

        OpenStackInstance currentInstance = instanceService.findOne(id);
        if (currentInstance == null) {
            logger.error("Unable to delete. OpenStackInstance with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Unable to delete. OpenStackInstance with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        instanceService.delete(id);
        return new ResponseEntity<OpenStackUser>(HttpStatus.NO_CONTENT);
    }

    // ------------------- Delete All Users-----------------------------

    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    public ResponseEntity<OpenStackUser> deleteAllFoodItem() {
        logger.info("Deleting All Users");

        instanceService.deleteAll();
        return new ResponseEntity<OpenStackUser>(HttpStatus.NO_CONTENT);
    }
}
