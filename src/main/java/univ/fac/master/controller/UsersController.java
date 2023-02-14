package univ.fac.master.controller;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import univ.fac.master.entities.AuthenticationBean;
import univ.fac.master.entities.Users;
import univ.fac.master.repositories.UsersRepository;
import univ.fac.master.service.UsersService;

@CrossOrigin(origins ="http://localhost:4200/")
@RestController
public class UsersController {

	@Autowired
	private UsersRepository ur;
	
	@Autowired
	private UsersService us;

	@GetMapping(path = "/basicauth")
    public AuthenticationBean basicauth() {
        return new AuthenticationBean("You are authenticated");
    }
	
	@PostMapping("addUsers")
	public Users addUsers(@RequestBody Users users, HttpServletRequest request) throws UnsupportedEncodingException, MessagingException {
		
        Users u =us.register(users);
		us.sendVerificationEmail(users,"http://localhost:4200");

		return u;
	}
	@GetMapping("verify1/{code}")
	public String verifyUser1(@PathVariable String code) {
		System.out.println("zertt"+code);
	    if (us.verify(code)) {
	    	
	        return "<div class='container text-center'>"+
	       " <h3>Congratulations, your account has been verified.</h3>"+
	        "<a href='http://localhost:4200'>login for home</a>"+
	    "</div>";
	    } else {
	        return "<h1> Sorry, we could not verify account. It maybe already verified,"
	        		+ "        or verification code is incorrect "
	        		+ "<a href='http://localhost:4200/add'>reteurn pour repter inscription</a>";
	    }
	}
	
	@GetMapping("user/{email}")
	public Users getUserByEmail(@PathVariable String email){
		return us.getUserByEmail(email);
	}
	
	
	 @GetMapping("/verify/{code}")
	    public AuthenticationBean verifyUser(@PathVariable String code) {
		 System.out.println(code);
	        if (us.verify(code)) {
	        
	            return new AuthenticationBean("1");
	        } else {
	            return new AuthenticationBean("0");
	        }
	    }

}
