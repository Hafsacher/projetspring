package univ.fac.master.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import univ.fac.master.entities.CustomUsersDetails;
import univ.fac.master.entities.Users;
import univ.fac.master.repositories.UsersRepository;

public class CustomUsersDetailsService implements UserDetailsService {
	
	@Autowired
	private UsersRepository ur;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
       Users users = ur.findByEmail(email);
		
		if (users == null) {
			throw new UsernameNotFoundException("Etudiant not found");
		}
		
		return new CustomUsersDetails(users);
	
	}

}
