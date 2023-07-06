package fr.thesakyo.TPSpring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.thesakyo.TPSpring.model.Post;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {}
