package com.examplemongo.workshopmongo.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.examplemongo.workshopmongo.domain.User;

@RestController //Recurso REST
@RequestMapping(value = "/users") //é o caminho do endpoint. Normalmente é o nome do recurso no plural
public class UserResource {

	@RequestMapping(method=RequestMethod.GET) //para dizer que esse método é o endpoint REST no caminho /users. Poderia utilizar o @GetMapping...	
	public ResponseEntity<List<User>> findAll() { //o ResponseEntity ele retorna um objeto sofisticado que encapsula toda a estrutura necessária para retornar respostas HTTP já com cabeçalhos e erros...
		//public List<User> findAll() {
		//para fazer teste vamos instanciar alguns usuários
		User maria = new User("1", "Maria Brown", "maria@gmail.com");
		User alex = new User("2", "Alex Green", "alex@gmail.com");
		List<User> list = new ArrayList<>();
		list.addAll(Arrays.asList(maria, alex));
		return ResponseEntity.ok().body(list);
		//o ok retorna o código de resposta em http de que ocorreu com sucesso
		//body é o corpo da resposta
	}	

}
