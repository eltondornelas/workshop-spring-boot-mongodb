package com.examplemongo.workshopmongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.examplemongo.workshopmongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
	
	List<Post> findByTitleContainingIgnoreCase(String text);
	//só com essa declaração já faz com que o Spring data monte a consulta
	//aqui precisa seguir o nome da função de acordo com o spring, qualquer dúvida olhar o link no material de apoio 
}
