package ch.uzh.ifi.seal.soprafs19.repository;

import ch.uzh.ifi.seal.soprafs19.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("userRepository")
public interface UserRepository extends CrudRepository<User, Long> {
	User findById(long id);
	User findByName(String name);
	User findByUsername(String username);
	User findByBirthday(String birthday);
	User findByPassword(String password);
	User findByToken(String token);
	Boolean existsUserById(long id);
	Boolean existsUserByUsername(String username);

}
