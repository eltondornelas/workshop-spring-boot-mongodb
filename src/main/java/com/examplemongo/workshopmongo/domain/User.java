package com.examplemongo.workshopmongo.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="user") //informa que a classe User corresponde a uma coleção do MongoDB (no caso a coleção user). como é o mesmo nome não precisaria colocar o nome collection, pois ele procura a classe em nome minusculo
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id //em cima do atributo que é a chave.
	private String id;
	private String name;
	private String email;

	@DBRef(lazy = true) //para informar que é uma referência. o lazy=true é para que só acesse os posts se explicitamente acessados, pois pode causar um transferência de dados pesada se for apenas uma consulta de usuário
	private List<Post> posts = new ArrayList<>();
	//vamos colocar os posts do usuário apenas referenciados e não aninhados
	//ao iniciar uma associação e é uma coleção, é interessante iniciar logo
	
	public User() {
		
	}

	public User(String id, String name, String email) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
		
}
