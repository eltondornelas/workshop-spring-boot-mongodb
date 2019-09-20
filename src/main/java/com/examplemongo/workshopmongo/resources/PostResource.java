package com.examplemongo.workshopmongo.resources;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.examplemongo.workshopmongo.domain.Post;
import com.examplemongo.workshopmongo.resources.util.URL;
import com.examplemongo.workshopmongo.services.PostService;

@RestController //Recurso REST
@RequestMapping(value = "/posts") //é o caminho do endpoint. Normalmente é o nome do recurso no plural
public class PostResource {

	//o controlador REST acessa o serviço e o serviço acessa o repositório, como está no desenho.
	@Autowired
	private PostService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET) 
	public ResponseEntity<Optional<Post>> findById(@PathVariable String id) { //para casar com o id da URL
		Optional<Post> obj = service.findById(id);
		return ResponseEntity.ok().body(obj);		
	}
	
	@RequestMapping(value="/titlesearch", method=RequestMethod.GET) 
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value="text", defaultValue="") String text) {
		text = URL.decodeParam(text);
		List<Post> list = service.findByTitle(text);
		return ResponseEntity.ok().body(list);
    }
}