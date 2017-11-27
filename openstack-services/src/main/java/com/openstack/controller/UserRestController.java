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

import com.openstack.model.OpenStackUser;
import com.openstack.service.UserService;
import com.openstack.util.CustomErrorType;
import com.openstack.util.CustomMessageType;


@RestController
@RequestMapping("/users")
public class UserRestController {
    @Autowired
    private UserService userservice;
  

    public static final Logger logger = LoggerFactory.getLogger(UserRestController.class);

  
    // -------------------Retrieve All Users ---------------------------------------

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<OpenStackUser>> listAllUsers() {
        List<OpenStackUser> products = (List<OpenStackUser>) userservice.findAll();
        if (products.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<OpenStackUser>>(products, HttpStatus.OK);
    }

    // -------------------Retrieve one OpenStackUser------------------------------------------

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> listUserById(@PathVariable("id") Long id) {
        logger.info("Fetching OpenStackUser with id {}", id);
        OpenStackUser user = userservice.findOne(id);
        if (user == null) {
            logger.error("OpenStackUser with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("OpenStackUser with id " + id
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<OpenStackUser>(user, HttpStatus.OK);
    }
    
    
    // -------------------Retrieve one OpenStackUser By UserName && Password ------------------------------------------

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> listUserById(@RequestBody OpenStackUser loginUser) {
        logger.info("Fetching OpenStackUser with username{}", loginUser);
        OpenStackUser user = userservice.findOne(Example.<OpenStackUser>of(loginUser, ExampleMatcher.matching()
                .withMatcher("username", ExampleMatcher.GenericPropertyMatchers.ignoreCase())));
        if (null != user && user.getPassword().equals(loginUser.getPassword())) {
            
            return new ResponseEntity<CustomMessageType>(new CustomMessageType("Success"), HttpStatus.OK);
        }
        logger.error("OpenStackUser with username {} not found.", loginUser.getUsername());
        return new ResponseEntity<CustomErrorType>(new CustomErrorType("Failure"), HttpStatus.OK);
    }

    // -------------------Create a OpenStackUser -------------------------------------------

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody OpenStackUser user, UriComponentsBuilder ucBuilder) {
        logger.info("Creating OpenStackUser : {}", user);
        userservice.save(user);
        return new ResponseEntity<OpenStackUser>(user, HttpStatus.CREATED);
    }

    // ------------------- Update a OpenStackUser ------------------------------------------------

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @RequestBody OpenStackUser user) {
        logger.info("Updating OpenStackUser with id {}", id);

        OpenStackUser currentUser = userservice.findOne(id);
        currentUser.setUsername(user.getUsername());
        currentUser.setPassword(user.getPassword());
        if (currentUser == null) {
            logger.error("Unable to update. OpenStackUser with id {} not found.", id);
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("Unable to upate. OpenStackUser with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        userservice.save(currentUser);
        return new ResponseEntity<OpenStackUser>(currentUser, HttpStatus.OK);
    }

    // ------------------- Delete a OpenStackUser ------------------------------------

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteFoodItem(@PathVariable("id") Long id) {
        logger.info("Fetching & Deleting OpenStackUser with id {}", id);

        OpenStackUser currentUser = userservice.findOne(id);
        if (currentUser == null) {
            logger.error("Unable to delete. OpenStackUser with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Unable to delete. OpenStackUser with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        userservice.delete(id);
        return new ResponseEntity<OpenStackUser>(HttpStatus.NO_CONTENT);
    }

    // ------------------- Delete All Users-----------------------------

    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    public ResponseEntity<OpenStackUser> deleteAllFoodItem() {
        logger.info("Deleting All Users");

        userservice.deleteAll();
        return new ResponseEntity<OpenStackUser>(HttpStatus.NO_CONTENT);
    }
}
