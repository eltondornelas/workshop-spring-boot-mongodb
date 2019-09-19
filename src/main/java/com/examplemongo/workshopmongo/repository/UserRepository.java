package com.examplemongo.workshopmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.examplemongo.workshopmongo.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
	//primeiro argumento é o tipo da classe de domínio que vai gerenciar e o segundo é o tipo do id da classe dele que é String id em user.

	
}
