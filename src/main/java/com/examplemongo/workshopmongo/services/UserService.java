package com.examplemongo.workshopmongo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examplemongo.workshopmongo.domain.User;
import com.examplemongo.workshopmongo.repository.UserRepository;

@Service //para que o spring entenda que essa classe é um serviço que pode ser injetável em outras classes
public class UserService {

	@Autowired //instancia o objeto automaticamente no serviço
	private UserRepository repo;
	
	
	public List<User> findAll() {
		//essa função vai ser para buscar no banco de dados
		//Serviço conversa com repositório
		return repo.findAll(); //do MongoRepository
	}
}
