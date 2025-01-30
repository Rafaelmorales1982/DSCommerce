package com.devsuperior.dscommerce.services;






import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dscommerce.dto.ProductDTO;
import com.devsuperior.dscommerce.entities.Product;
import com.devsuperior.dscommerce.repositories.ProductRepository;

//nesse cara vai implementar no Banco de Dados
@Service
public class ProductService {
	@Autowired
	private ProductRepository repository;

	@Transactional(readOnly = true)
	public ProductDTO findById(Long id) {
		;
		Product product = repository.findById(id).get();
		return new ProductDTO(product);
	
		
	}
	
	
 /*Listar tos os produtos com paginação*/
	@Transactional(readOnly = true)
	public Page<ProductDTO> findAll(Pageable pageable) {//bucar todos os produtos
		
		Page<Product> result = repository.findAll(pageable);
		return result.map(x -> new ProductDTO(x));
	
		
	}
	
	//Criando um método para não repetir os mesmos campos assim ficar com código mais limpo
	private void copyDtoToEntity(ProductDTO dto, Product entity) {
		entity.setName(dto.getName());//copiando para entity
		entity.setDescription(dto.getDescription());
		entity.setPrice(dto.getPrice());
		entity.setImgUrl(dto.getImgUrl());
		
	}
	
	//inserir dados
	@Transactional
	public ProductDTO insert(ProductDTO dto) { //inserindo no banco de dados
		Product entity = new Product();
		//instanciando o método
		copyDtoToEntity(dto , entity);
		
		//salvando no banco no repositiry
		entity = repository.save(entity);
		 return new ProductDTO(entity);
	}
	
	//Update dados
	@Transactional   
	public ProductDTO update(Long id, ProductDTO dto) { //atualizando no banco de dados
		Product entity = repository.getReferenceById(id);//instanciando um id para atualizar o banco 
		
		//instanciando o método
		copyDtoToEntity(dto , entity);
		
	
		//salvando no banco no repositiry
		entity = repository.save(entity);
		 return new ProductDTO(entity);
	}



	

	
}
