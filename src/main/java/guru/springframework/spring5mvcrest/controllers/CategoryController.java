package guru.springframework.spring5mvcrest.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import guru.springframework.spring5mvcrest.api.v1.model.CategoryDTO;
import guru.springframework.spring5mvcrest.api.v1.model.CategoryListDTO;
import guru.springframework.spring5mvcrest.services.CategoryService;

@Controller
@RequestMapping("/api/v1/categories")
public class CategoryController {
	
	private final CategoryService categoryService;
	
	public CategoryController(CategoryService categoryService) {
		this.categoryService=categoryService;
	}
	
	@GetMapping
	public ResponseEntity<CategoryListDTO> getallCategories(){
		
	return new ResponseEntity<CategoryListDTO>(new CategoryListDTO(categoryService.getAllCategories()),HttpStatus.OK);
	}
	
	@GetMapping("{name}")
	public ResponseEntity<CategoryDTO> getCategoryByName(@PathVariable String name){
		return new ResponseEntity<CategoryDTO>(
			categoryService.getCategoryByName(name),HttpStatus.OK);
		}
	}

