package com.example.Team2.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.Team2.Entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, String>{
	User findByEmail(String email);
}
