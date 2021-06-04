package br.com.kdev.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.kdev.dto.ProductDTO;
import br.com.kdev.exception.CategoryNotFoundException;
import br.com.kdev.exception.ProductNotFoundException;
import br.com.kdev.model.Product;
import br.com.kdev.model.dto.DTOConverter;
import br.com.kdev.repository.CategoryRepository;
import br.com.kdev.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;

	public List<ProductDTO> getAll() {
		List<Product> products = productRepository.findAll();
		return products.stream().map(DTOConverter::convert).collect(Collectors.toList());
	}

	public List<ProductDTO> getProductByCategoryId(Long categoryId) {
		List<Product> products = productRepository.getProductByCategory(categoryId);
		return products.stream().map(DTOConverter::convert).collect(Collectors.toList());
	}

	public ProductDTO findByProductIdentifier(String productIdentifier) {
		Product product = productRepository.findByProductIdentifier(productIdentifier);
		if (product != null) {
			return DTOConverter.convert(product);
		}
		throw new ProductNotFoundException();
	}

	public ProductDTO save(ProductDTO productDTO) {
		Boolean existsCategory = categoryRepository.existsById(productDTO.getCategoryDTO().getId());
		if (!existsCategory) {
			throw new CategoryNotFoundException();
		}
		Product product = productRepository.save(Product.convert(productDTO));
		return DTOConverter.convert(product);
	}

	public ProductDTO delete(long ProductId) {
		Optional<Product> product = productRepository.findById(ProductId);
		if (product.isPresent()) {
			productRepository.delete(product.get());
		}
		return null;
	}

}
