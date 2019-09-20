package com.examplemongo.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examplemongo.workshopmongo.domain.User;
import com.examplemongo.workshopmongo.repository.UserRepository;
import com.examplemongo.workshopmongo.services.exception.ObjectNotFoundException;

@Service //para que o spring entenda que essa classe é um serviço que pode ser injetável em outras classes
public class UserService {

	@Autowired //instancia o objeto automaticamente no serviço
	private UserRepository repo;
	
	
	public List<User> findAll() {
		//essa função vai ser para buscar no banco de dados
		//Serviço conversa com repositório
		return repo.findAll(); //do MongoRepository
	}
	
	public User findById(String id) {
		Optional<User> obj = repo.findById(id); //passa o id e ele retorna o usuário
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));		
	}
}
