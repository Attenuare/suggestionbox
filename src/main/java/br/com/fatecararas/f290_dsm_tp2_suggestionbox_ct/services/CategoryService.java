package br.com.fatecararas.f290_dsm_tp2_suggestionbox_ct.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.fatecararas.f290_dsm_tp2_suggestionbox_ct.config.exceptions.ObjectNotFoundException;
import br.com.fatecararas.f290_dsm_tp2_suggestionbox_ct.model.entities.Category;
import br.com.fatecararas.f290_dsm_tp2_suggestionbox_ct.repositories.CategoryRepository;

@Service
public class CategoryService {

    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public Category save(Category category) {
        return repository.save(category);
    }

    public Category update(Integer id, Category pCategory) {
        Category category = searchById(id);
        category.setDescription(pCategory.getDescription());
        return repository.save(category);

    }

    public Category searchById(Integer pId) {
        Optional<Category> optional = repository.findById(pId);
        if (optional.isEmpty()) {
            throw new ObjectNotFoundException("Category not found. ID: " + pId);
        }
        return optional.get();
    }

    public List<Category> findAll() {
        return repository.findAll();
    }

    public void delete(Integer id) {
        Category category = searchById(id);
        repository.deleteById(category.getId());
    }
}
