package com.userlocation.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.userlocation.users.entity.UserLocation;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserLocation, Long> {

//	@Query(value="select id FROM user_location",nativeQuery=true)
//	public List<UserLocation> getAllUser(int n);

}
