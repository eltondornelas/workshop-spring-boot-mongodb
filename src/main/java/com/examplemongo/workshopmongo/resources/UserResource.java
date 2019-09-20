package com.examplemongo.workshopmongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.examplemongo.workshopmongo.domain.User;
import com.examplemongo.workshopmongo.dto.UserDTO;
import com.examplemongo.workshopmongo.services.UserService;

@RestController //Recurso REST
@RequestMapping(value = "/users") //é o caminho do endpoint. Normalmente é o nome do recurso no plural
public class UserResource {

	//o controlador REST acessa o serviço e o serviço acessa o repositório, como está no desenho.
	@Autowired
	private UserService service;
	
	@RequestMapping(method=RequestMethod.GET) //para dizer que esse método é o endpoint REST no caminho /users. Poderia utilizar o @GetMapping...	
	public ResponseEntity<List<UserDTO>> findAll() { //o ResponseEntity ele retorna um objeto sofisticado que encapsula toda a estrutura necessária para retornar respostas HTTP já com cabeçalhos e erros...
		//public List<User> findAll() {
		//para fazer teste vamos instanciar alguns usuários
		//User maria = new User("1", "Maria Brown", "maria@gmail.com");
		//User alex = new User("2", "Alex Green", "alex@gmail.com");
		
		//não precisa instanciar mais na mão
		List<User> list = service.findAll(); //busca no banco de dados os usuários para guardar na lista
		//list.addAll(Arrays.asList(maria, alex));
		
		List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList()); //converte cada objeto da lista para um DTO
		return ResponseEntity.ok().body(listDto);
		//o ok retorna o código de resposta em http de que ocorreu com sucesso
		//body é o corpo da resposta. Vai ter a resposta list
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET) 
	public ResponseEntity<UserDTO> findById(@PathVariable String id) { //para casar com o id da URL

		User obj = service.findById(id);
		return ResponseEntity.ok().body(new UserDTO(obj));
	}
	
	@RequestMapping(method=RequestMethod.POST) //poderia ser @PostMapping 
	public ResponseEntity<Void> insert(@RequestBody UserDTO objDto) { //para casar com o id da URL
		User obj = service.fromDTO(objDto);
		//converte DTO para User

		obj = service.insert(obj);
		//Retornar uma resposta vazia porém com um cabeçalho com o URI do novo recurso criado
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		//pega o endereço do no objeto que inseriu
		
		return ResponseEntity.created(uri).build();
		//created retorna o códito http quando cria um novo recurso
	}
	
	
	
}
