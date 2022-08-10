package jana60.repository;

import org.springframework.data.repository.CrudRepository;

import jana60.model.Utente;

public interface UserRepository extends CrudRepository<Utente, Integer> {

}
