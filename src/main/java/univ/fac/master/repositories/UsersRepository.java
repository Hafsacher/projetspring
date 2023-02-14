package univ.fac.master.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import univ.fac.master.entities.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {

	@Query("SELECT u FROM Users u WHERE u.email = ?1")
	public Users findByEmail(String email);
	@Query("SELECT u FROM Users u WHERE u.verificationCode = ?1")
    public Users findByVerificationCode(String code);
	@Query("UPDATE Users u SET u.enabled = true WHERE u.id = ?1")
	@Modifying
	public void enabled(Integer id);
}
