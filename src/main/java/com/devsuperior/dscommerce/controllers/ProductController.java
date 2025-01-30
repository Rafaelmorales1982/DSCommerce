package com.devsuperior.dscommerce.controllers;





import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	public ResponseEntity<ProductDTO>  findById(@PathVariable Long id){
		ProductDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
		
	}
	
	//ao buscar todos os produtos deixar de modo paginado 
	@GetMapping
	public ResponseEntity<Page<ProductDTO>> findAll(Pageable pageable){
	Page<ProductDTO> dto = service.findAll(pageable);
	return ResponseEntity.ok(dto);
	}
	
	
	//inserindo no banco
	@PostMapping
	public ResponseEntity<ProductDTO> insert(@RequestBody ProductDTO dto){//config corpo da requisição
		dto = service.insert(dto);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
			.buildAndExpand(dto.getId()).toUri();
			return ResponseEntity.created(uri).body(dto);
		}
	
	//Atualizando um registro no banco
	 @PutMapping(value = "/{id}")
	    public ResponseEntity<ProductDTO> update(@PathVariable Long id, @RequestBody ProductDTO dto) {
	        dto = service.update(id, dto);
	        return ResponseEntity.ok(dto);
	    }
		 
	 @DeleteMapping(value = "/{id}")
	    public ResponseEntity<Void> delete(@PathVariable Long id) { //Não precisa de corpo @RequestBody ProductDTO dto
	        service.delete(id);
	        return ResponseEntity.noContent().build();
	    }
		
}
