package com.student.project.amazone.Admin_controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.student.project.amazone.File.UploadService.FileStorageService;
import com.student.project.amazone.entity.Catagory_model;
import com.student.project.amazone.entity.Product_model;
import com.student.project.amazone.service.ServiceProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@RestController

@RequestMapping("/api/v2/product/")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:9111")
public class
ControllerProduct_mn {

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

    public String getRamdom(){
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 15;
        Random random = new Random();

        String fileName = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return fileName;
    }

	@PostMapping("/save")
	public ResponseEntity create(@RequestParam("image") MultipartFile file, @RequestParam("product") String product) throws JsonProcessingException {

        String imageName = "product.jpg";

        if (file != null) {
            imageName = fileStorageService.storeFileBanner(file,getRamdom());
        }

        ObjectMapper objectMapper = new ObjectMapper();

// Deserialization into the `Employee` class
        Product_model emp = null;
        emp = objectMapper.readValue(product, Product_model.class);
        System.out.println(emp.getCatagory());
        Catagory_model cata = new Catagory_model();

        cata.setId(emp.getCatagory().getId());
        emp.setImageurl(imageName);
        emp.setCatagory(cata);
        return ResponseEntity.ok(serviceProduct.save(emp));
    }

	@PutMapping("/update")
    public ResponseEntity update(@RequestParam("image") MultipartFile file, @RequestParam("product") String product) throws JsonProcessingException {

        String imageName = "product.jpg";


        ObjectMapper objectMapper = new ObjectMapper();

        // Deserialization into the `Employee` class
        Product_model emp = null;
        emp = objectMapper.readValue(product, Product_model.class);

        Product_model getFromData = serviceProduct.findById(emp.getId()).get();

        if (file != null) {
            imageName = fileStorageService.storeFileBanner(file,getFromData.getImageurl());
        }

        Catagory_model cata = new Catagory_model();

        cata.setId(emp.getCatagory().getId());
        emp.setImageurl(imageName);
        emp.setCatagory(cata);
        return ResponseEntity.ok(serviceProduct.save(emp));
    }

    @GetMapping("{id}")
    public ResponseEntity<Product_model> findById(@PathVariable Long id) {
        Optional<Product_model> product = serviceProduct.findById(id);
        if (!product.isPresent()) {
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(product.get());
    }

//    @PutMapping("/update")
//    public ResponseEntity<Product_model> updateCategory(@RequestBody Product_model cata) {
//        Product_model findProduct = serviceProduct.findById(cata.getId()).get();
//        cata.setImageurl(findProduct.getImageurl());
//        Product_model updateCategory = serviceProduct.save(cata);
//        return new ResponseEntity<>(updateCategory, HttpStatus.OK);
//    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable("id") Long id) {
        serviceProduct.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
