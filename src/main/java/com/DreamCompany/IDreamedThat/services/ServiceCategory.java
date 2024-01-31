package com.DreamCompany.IDreamedThat.services;

import com.DreamCompany.IDreamedThat.models.Category;
import java.util.Optional;

public interface ServiceCategory {

    void save(Category category);

    Optional<Category> findById(Long id);
}
