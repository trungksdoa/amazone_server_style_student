package com.student.project.amazone.service.User_feature;

import com.student.project.amazone.entity.Product_model;
import com.student.project.amazone.repo.Product_modelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceProduct {
	@Autowired
	Product_modelRepository repositoryProduct;

	public List<Product_model> findAll() {
		List<Product_model> result = new ArrayList<Product_model>();
		repositoryProduct.findAll().forEach(result::add);
		return result;
	}

	public List<Product_model> findProductsByName(String name) {
		List<Product_model> result = new ArrayList<Product_model>();
		repositoryProduct.findProductsByName(name).forEach(result::add);
		return result;
	}

	public Optional<Product_model> findById(Long id) {
		return repositoryProduct.findById(id);
	}

	public Product_model save(Product_model stock) {
		return repositoryProduct.save(stock);
	}

	public Product_model Update(Product_model newProduct, Long id) {
		Product_model oldProduct = repositoryProduct.findById(id).get();
		if (!newProduct.getName().isEmpty())
			oldProduct.setName(newProduct.getName());
		if (!newProduct.getDescription().isEmpty())
			oldProduct.setDescription(newProduct.getDescription());
		if (newProduct.getPrice() > 0)
			oldProduct.setPrice(newProduct.getPrice());

		return repositoryProduct.save(oldProduct);

	}

	public void deleteById(Long id) {
		repositoryProduct.deleteById(id);
	}

}
