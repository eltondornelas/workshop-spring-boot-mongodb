package com.examplemongo.workshopmongo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.examplemongo.workshopmongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
	
	//@Query("{ <field>: { $regex: /pattern/, $options: '<options>' } }")
	@Query("{ 'title': { $regex: ?0, $options: 'i' } }") //?0 por ser o primeiro parâmetro que vem no método, se quisesse o segundo parâmetro seria o ?1
	List<Post> searchTitle(String text); //nesse pode colocar o nome que quiser já que será personalizado 
	
	List<Post> findByTitleContainingIgnoreCase(String text);
	//só com essa declaração já faz com que o Spring data monte a consulta
	//aqui precisa seguir o nome da função de acordo com o spring, qualquer dúvida olhar o link no material de apoio
	
	//@Query("{ $and: [ { <expression1> }, { <expression2> } , ... , { <expressionN> } ] }")
	@Query("{ $and: [ {date: {$gte: ?1} }, { date: { $lte: ?2} } , { $or: [ { 'title': { $regex: ?0, $options: 'i' } }, { 'body': { $regex: ?0, $options: 'i' } }, { 'comments.text': { $regex: ?0, $options: 'i' } } ] } ] }")
	List<Post> fullSearch(String text, Date minDate, Date maxDate);
	//como pode ser no titulo, nos comentarios ou no post, tem que usar o $or
	//para testar a data minima e maxima vai ter que ser um $and
	//gte -> maior ou igual (greater than or equal)
	//lte -> menor ou igual (less than or equal)
	//o campo date é o atributo do Post
	//comments é uma lista, por isso precisa ir buscar o atributo interno dele
	
}
