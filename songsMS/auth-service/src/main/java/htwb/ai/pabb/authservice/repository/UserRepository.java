package htwb.ai.pabb.authservice.repository;

import htwb.ai.pabb.authservice.models.User;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, Integer> {
}
