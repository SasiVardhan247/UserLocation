package com.userlocation.users.controller;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.security.ConditionalOnDefaultWebSecurity;
import org.springframework.web.bind.annotation.*;
//import org.springframework.security.access.prepost.PreAuthorize;


//import java.awt.PageAttributes.MediaType;
import java.util.*;
//import java.util.Optional;

import com.userlocation.users.entity.UserLocation;
import com.userlocation.users.repository.*;


import com.userlocation.users.dao.*;

@RestController
public class UserController {
	
	 @Autowired
	    private UserRepository userRepository;
	 
	 	@PostMapping(value = "/create_data")
//	 	@PreAuthorize("hasRole('ADMIN')")
		public UserLocation createTable(@RequestBody UserLocationDao userDao) {
			
			UserLocation userloc = new UserLocation();
			//customerEntity.setId(customerDao.getId());
			userloc.setName(userDao.getName());
			userloc.setLatitude(userDao.getLatitude());
			userloc.setLongitude(userDao.getLongitude());
			
			return userRepository.save(userloc);
		}

	    @PutMapping("/update_data")
//	    @PreAuthorize("hasRole('ADMIN')")
	    public UserLocation updateTable(@RequestBody UserLocationDao userDao) {
	    	Optional<UserLocation> user = userRepository.findById(userDao.getId());
	    	UserLocation userloc = user.get();
	    	
	    	userloc.setName(userDao.getName());
			userloc.setLatitude(userDao.getLatitude());
			userloc.setLongitude(userDao.getLongitude());
			
			return userRepository.save(userloc);
	    }
	 	
	    @GetMapping("/get_users/{n}")
	    public List<UserLocation> getUsers(@PathVariable int n) {
	        double originLat = 0.0;
	        double originLon = 0.0;
	        
	        List<UserLocation> allUsers = userRepository.findAll();
	        HashMap<UserLocation, Double> userDistances = new HashMap<>();
	        
	        // calculate distance of each user from the origin
	        for (UserLocation user : allUsers) {
	            double userLat = user.getLatitude();
	            double userLon = user.getLongitude();
	            double distance = calculateDistance(originLat, originLon, userLat, userLon);
	            userDistances.put(user, distance);
	        }

	        List<Map.Entry<UserLocation, Double>> sortedUserDistances = new ArrayList<>(userDistances.entrySet());
	        sortedUserDistances.sort(Map.Entry.comparingByValue());
	        
	        // extract the first n users
	        List<UserLocation> nearestUsers = new ArrayList<>();
	        for (int i = 0; i < n && i < sortedUserDistances.size(); i++) {
	            nearestUsers.add(sortedUserDistances.get(i).getKey());
	        }
	        
	        return nearestUsers;
	    }

	    private double calculateDistance(double originLat, double originLon, double userLat, double userLon) {
	        double earthRadius = 6371; // in kilometers

	        double latDistance = Math.toRadians(userLat - originLat);
	        double lonDistance = Math.toRadians(userLon - originLon);

	        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
	                + Math.cos(Math.toRadians(originLat)) * Math.cos(Math.toRadians(userLat))
	                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);

	        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

	        return earthRadius * c * 1000; // in meters
	    }




	    
}

