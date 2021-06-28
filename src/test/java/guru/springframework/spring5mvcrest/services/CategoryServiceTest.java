package guru.springframework.spring5mvcrest.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import guru.springframework.spring5mvcrest.api.v1.mapper.CategoryMapper;
import guru.springframework.spring5mvcrest.api.v1.model.CategoryDTO;
import guru.springframework.spring5mvcrest.domain.Category;
import guru.springframework.spring5mvcrest.repositories.CategoryRepository;

public class CategoryServiceTest {

	public static final Long ID=2L;
	public static final String NAME="Syed";
	CategoryService categoryService;
	
	@Mock
	CategoryRepository categoryRepository;
	
	@BeforeEach
	public void setUp() throws Exception{
		MockitoAnnotations.initMocks(this.getClass());
		categoryService=new CategoryServiceImpl(CategoryMapper.INSTANCE,categoryRepository);
	}
	
	@Test
	public void getAllCategories() throws Exception {
		
		//given
		List<Category> categories = Arrays.asList(new Category(),new Category(),new Category());
		Mockito.when(categoryRepository.findAll()).thenReturn(categories);
		
		//when
		List<CategoryDTO> categoryDTOS = categoryService.getAllCategories();
		
		//then
		assertEquals(3,categoryDTOS.size());
		
	}
	
	@Test
	public void getCategoryByName() throws Exception{
		
		//given
		Category category = new Category();
		category.setId(ID);
		category.setName(NAME);
		
		Mockito.when(categoryRepository.findByName(Mockito.anyString())).thenReturn(category);
		
		//when
		CategoryDTO categoryDTO = categoryService.getCategoryByName(NAME);
		
		//then
		assertEquals(ID,categoryDTO.getId());
		assertEquals(NAME,categoryDTO.getName());
	}
}
