package guru.springframework.spring5mvcrest.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import guru.springframework.spring5mvcrest.api.v1.mapper.CategoryMapper;
import guru.springframework.spring5mvcrest.api.v1.model.CategoryDTO;
import guru.springframework.spring5mvcrest.repositories.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService{

	private final CategoryMapper categoryMapper;
	private final CategoryRepository categoryRepository;
	
	

	public CategoryServiceImpl(CategoryMapper categoryMapper, CategoryRepository categoryRepository) {
		super();
		this.categoryMapper = categoryMapper;
		this.categoryRepository = categoryRepository;
	}

	@Override
	public List<CategoryDTO> getAllCategories() {
		// TODO Auto-generated method stub
		return categoryRepository.findAll()
				.stream()
				.map(categoryMapper::categoryToCategoryDTO)
				.collect(Collectors.toList());
	}

	@Override
	public CategoryDTO getCategoryByName(String name) {
		// TODO Auto-generated method stub
		return categoryMapper.categoryToCategoryDTO(categoryRepository.findByName(name));
	}

}
