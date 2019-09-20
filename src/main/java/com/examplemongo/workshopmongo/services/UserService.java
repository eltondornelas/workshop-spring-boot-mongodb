package com.examplemongo.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examplemongo.workshopmongo.domain.User;
import com.examplemongo.workshopmongo.dto.UserDTO;
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
	
	public User insert(User obj) {
		return repo.insert(obj);
	}
	
	public void delete(String id) {
		findById(id); //para fazer a busca primeiro e caso não existe ele já gera a exceção.
		repo.deleteById(id);
	}
	
	public User update(User obj) {
		//esse obj ainda não tem vinculo com o banco de dados		
		User newObj = findById(obj.getId()); //com isso ele busca no DB
		updateData(newObj, obj); //copia os novos dados do obj para o newObj
		return repo.save(newObj);
	}
	
	private void updateData(User newObj, User obj) {
		
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
		//o id não muda
		
	}

	//Pega um DTO e instancia um usuário é o contrário do "UserDTO". 
	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
	}
	
	
	
}
