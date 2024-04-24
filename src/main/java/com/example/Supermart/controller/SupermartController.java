package com.example.Supermart.controller;

import com.example.Supermart.model.Supermart;
import com.example.Supermart.repo.SupermartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController

public class SupermartController {

	@Autowired
	private SupermartRepo supermartRepo;

	@GetMapping("/getAllProducts")
	public ResponseEntity<List<Supermart>> getAllProducts() {
		List<Supermart> supermartList;
		supermartList = new ArrayList<>();
		supermartRepo.findAll().forEach(supermartList::add);
		if (supermartList.isEmpty()) {
			return new ResponseEntity<>(supermartList, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(supermartList, HttpStatus.OK);
	}

	@GetMapping("/getProductById/{id}")
	public ResponseEntity<Supermart> getProductById(@PathVariable Long id) {
		Optional<Supermart> productData = supermartRepo.findById(id);
		if (productData.isPresent()) {
			return new ResponseEntity<>(productData.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PostMapping("/addProduct")
	public ResponseEntity<Supermart> addProduct(@RequestBody Supermart product) {
		Supermart productObj = supermartRepo.save(product);
		return new ResponseEntity<>(productObj, HttpStatus.OK);
	}

	@PutMapping("/updateProductById/{id}")
	public ResponseEntity<Supermart> updateProductById(@PathVariable Long id, @RequestBody Supermart newSupermart) {
		Optional<Supermart> oldProductData = supermartRepo.findById(id);
		if (oldProductData.isPresent()) {
			Supermart updatedProductData = oldProductData.get();
			updatedProductData.setProductName(newSupermart.getProductName());
			updatedProductData.setPrice(newSupermart.getPrice());

			Supermart productObj = supermartRepo.save(updatedProductData);
			return new ResponseEntity<>(productObj, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/deleteProductById/{id}")
	public ResponseEntity<HttpStatus> deleteProductById(@PathVariable Long id) {
		supermartRepo.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);

	}
}
