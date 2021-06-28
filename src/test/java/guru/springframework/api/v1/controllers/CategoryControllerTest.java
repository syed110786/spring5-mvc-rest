package guru.springframework.api.v1.controllers;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import guru.springframework.spring5mvcrest.api.v1.model.CategoryDTO;
import guru.springframework.spring5mvcrest.controllers.CategoryController;
import guru.springframework.spring5mvcrest.services.CategoryService;

public class CategoryControllerTest {

	public static final String NAME = "JIM";
	
	@Mock
	CategoryService categoryService;
	
	@InjectMocks
	CategoryController categoryController;
	
	MockMvc mockMvc;
	
	@BeforeEach
	public void setUp() throws Exception{
		MockitoAnnotations.initMocks(this);
		mockMvc=MockMvcBuilders.standaloneSetup(categoryController).build();
	}
	
	@Test
	public void testListCategories() throws Exception{
		CategoryDTO category1=new CategoryDTO();
		category1.setId(1L);
		category1.setName(NAME);
		
		CategoryDTO category2=new CategoryDTO();
		category2.setId(2L);
		category2.setName("Bob");
		
		List<CategoryDTO> categories=Arrays.asList(category1,category2);
		Mockito.when(categoryService.getAllCategories()).thenReturn(categories);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/categories/")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.categories",org.hamcrest.Matchers.hasSize(2)));
		
	}
	
	@Test
	public void testGetByNameCategories() throws Exception{
		CategoryDTO category1 = new CategoryDTO();
		category1.setId(1L);
		category1.setName(NAME);
		
		Mockito.when(categoryService.getCategoryByName(Mockito.anyString())).thenReturn(category1);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/categories/Jim")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.name",org.hamcrest.Matchers.equalTo(NAME)));
	}
}
