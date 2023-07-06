package fr.thesakyo.TPSpring.repository;

import fr.thesakyo.TPSpring.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends CrudRepository<User,Long> {}
