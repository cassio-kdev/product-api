package br.com.kdev.model.dto;

import br.com.kdev.dto.CategoryDTO;
import br.com.kdev.dto.ProductDTO;
import br.com.kdev.model.Category;
import br.com.kdev.model.Product;

public class DTOConverter {
	
	public static CategoryDTO convert(Category category) {
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setId(category.getId());
		categoryDTO.setNome(category.getNome());
		return categoryDTO;
	}
	
	
	public static ProductDTO convert(Product product) {
		ProductDTO productDTO = new ProductDTO();
		productDTO.setNome(product.getNome());
		productDTO.setPreco(product.getPreco());
		productDTO.setProductIdentifier(product.getProductIdentifier());
		productDTO.setDescricao(product.getDescricao());
		if (product.getCategory() != null) {
			productDTO.setCategoryDTO(convert(product.getCategory()));
		}
		return productDTO;
	}

}
