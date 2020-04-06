package com.example.Team2.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Team2.Entity.User;
import com.example.Team2.Exception.ResourceNotFoundException;
import com.example.Team2.Repo.UserRepo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value="User Controller API")
public class UserController {
	
	Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserRepo urepo;
	
	@ApiOperation(value="Returns Hello")
	@ApiResponses(
			value = {
					@ApiResponse(code=200, message = "Success Message"),
					@ApiResponse(code=555, message = "Status code 555")
			})
	@RequestMapping("/hello")
	public String home() {
		logger.info("First Page");
		return "Hello";
	}
	
	@ApiOperation(value="Returns List of all users")
	@GetMapping("/getallusers")
	public List<User> getAllUsers(){
		logger.error("All Users");
		List<User> list = (List<User>) urepo.findAll();
		return list;
	}
	
	@ApiOperation(value="Returns a particular user")
	@GetMapping("/getuser/{email}")
	public User getUser(@PathVariable("email") String email)  throws ResourceNotFoundException {
		logger.info("Request {}",email);
		User user = urepo.findById(email)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for this email :: " + email));
		logger.info("Response {}", user);
		return user;
	}
	
	@ApiOperation(value="Add User")
	@PostMapping("/adduser")
	public User addUser(@RequestBody User user) {
		User u = urepo.save(user);
		logger.info("New User {}",u);
		return u;
	}
	
	@ApiOperation(value="Update particular user")
	@PutMapping("/updateuser/{email}")
    public ResponseEntity<User> updateEmployee(@PathVariable("email") String email,
         @RequestBody User user) throws ResourceNotFoundException {
        User u = urepo.findById(email)
        		.orElseThrow(() -> new ResourceNotFoundException("User not found for this email :: " + email));
        u.setName(user.getName());
        u.setCity(user.getCity());
        u.setState(user.getState());
        final User updatedUser = urepo.save(u);
        logger.info("Response {}", updatedUser);
        return ResponseEntity.ok(updatedUser);
    }
	
	@ApiOperation(value="Delete user")
	@DeleteMapping("/deleteuser/{email}")
    public String deleteEmployee(@PathVariable(value = "email") String email)
         throws ResourceNotFoundException {
        User employee = urepo.findById(email)
       .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + email));

        urepo.delete(employee);
        logger.info("User Deleted");
        return "Deleted Successfully";
    }
}
