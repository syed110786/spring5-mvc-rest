package guru.springframework.api.v1.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import guru.springframework.spring5mvcrest.api.v1.mapper.CategoryMapper;
import guru.springframework.spring5mvcrest.api.v1.model.CategoryDTO;
import guru.springframework.spring5mvcrest.domain.Category;

public class CategoryMapperTest {

	CategoryMapper categoryMapper = CategoryMapper.INSTANCE;
	
	@Test
	public void categoryToCategoryDTO() throws Exception{
		//given
		Category category = new Category();
		category.setName("Mariyam");
		category.setId(1L);
		
		//when
		CategoryDTO categoryDTO = categoryMapper.categoryToCategoryDTO(category);
		
		//then
		assertEquals(Long.valueOf(1L),categoryDTO.getId());
		assertEquals("Mariyam",categoryDTO.getName());
	}
}
