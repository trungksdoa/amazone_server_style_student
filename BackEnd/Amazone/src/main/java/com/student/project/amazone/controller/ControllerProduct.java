package com.student.project.amazone.controller;

import com.student.project.amazone.File.UploadService.FileStorageService;
import com.student.project.amazone.entity.Catagory_model;
import com.student.project.amazone.entity.Product_model;

import com.student.project.amazone.service.User_feature.ServiceProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@RestController

@RequestMapping("/api/v1/product/")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class
ControllerProduct {

	String subPath = "products";

	@Autowired
	ServiceProduct serviceProduct;


	@Autowired
	private FileStorageService fileStorageService;
	@GetMapping("all")
	public ResponseEntity<List<Product_model>> findAllCategory() {
		List<Product_model> cata = serviceProduct.findAll();
		return new ResponseEntity<>(cata, HttpStatus.OK);
	}
	@GetMapping
	public ResponseEntity<List<Product_model>> findAll(@RequestParam(value = "name", defaultValue = "") String name) {
		return ResponseEntity.ok(serviceProduct.findProductsByName(name));
	}


	@PostMapping("save")
	public ResponseEntity create(@RequestParam(name = "image",required = false) MultipartFile file, @RequestParam("name") String name,
			@RequestParam("description") String description, @RequestParam("price") Long price) {

		String imageName = "product.jpg";

		if (file != null) {
			imageName = fileStorageService.storeFile(file);
		}

		Product_model product = new Product_model();

		product.setName(name);
		product.setDescription(description);
		product.setPrice(price);
		product.setImageurl(imageName);

		return ResponseEntity.ok(serviceProduct.save(product));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Product_model> findById(@PathVariable Long id) {
		Optional<Product_model> product = serviceProduct.findById(id);
		if (!product.isPresent()) {
			ResponseEntity.badRequest().build();
		}

		return ResponseEntity.ok(product.get());
	}
	@PutMapping("/update")
	public ResponseEntity<Product_model> updateCategory(@RequestBody Product_model cata) {
		Product_model updateCategory = serviceProduct.save(cata);
		return new ResponseEntity<>(updateCategory, HttpStatus.OK);
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteCategory(@PathVariable("id") Long id) {
		serviceProduct.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}


}
