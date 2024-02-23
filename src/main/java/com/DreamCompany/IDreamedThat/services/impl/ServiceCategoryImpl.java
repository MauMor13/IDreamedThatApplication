package com.DreamCompany.IDreamedThat.services.impl;

import com.DreamCompany.IDreamedThat.models.Category;
import com.DreamCompany.IDreamedThat.repositories.RepositoryCategory;
import com.DreamCompany.IDreamedThat.services.ServiceCategory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServiceCategoryImpl implements ServiceCategory {
    private final RepositoryCategory repositoryCategory;

    public ServiceCategoryImpl(RepositoryCategory repositoryCategory) {
        this.repositoryCategory = repositoryCategory;
    }

    @Override
    public void save(Category category){
        repositoryCategory.save(category);
    }

    @Override
    public Optional<Category> findById(Long id) {
        return repositoryCategory.findById(id);
    }
}
