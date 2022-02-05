package security.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import security.model.User;

public interface UserDao extends CrudRepository<User, Long>{
	@Query("SELECT u from User u where u.name=:userName")
	public User findByUsername(@Param("userName") String userName);
}
