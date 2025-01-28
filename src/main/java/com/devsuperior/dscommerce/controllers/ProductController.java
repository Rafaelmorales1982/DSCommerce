package com.devsuperior.dscommerce.controllers;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dscommerce.dto.ProductDTO;

import com.devsuperior.dscommerce.services.ProductService;

//@RestController é uma ferramenta essencial para construir aplicações web, principalmente APIs REST, no Spring.
//Ela simplifica o processo de lidar com requisições e retornar dados,

@RestController

//Configurar a rota

@RequestMapping(value = "/products")
public class ProductController {
	/*@GetMapping é essencial para mapear requisições GET para métodos em seus controladores Spring. 
	É uma ferramenta básica para criar APIs REST que retornam dados.*/
	
	
	/*@Autowired é uma anotação do Spring que você usa para pedir ao framework
	 *  que ele "injete" as dependências necessárias em uma classe.*/
	
	@Autowired
	private ProductService service;
	//Busca o produto por id
	@GetMapping(value = "/{id}")
	public ProductDTO findById(@PathVariable Long id){
	return service.findById(id);	
		
	}
	
	//ao buscar todos os produtos deixar de modo paginado 
	@GetMapping
	public Page<ProductDTO> findAll(Pageable pageable){
	return service.findAll(pageable);
		
	}
	
	
	//inserindo no banco
	@PostMapping
	public ProductDTO insert(@RequestBody ProductDTO dto){//config corpo da requisição
		return service.insert(dto);
			
		}
		

}
