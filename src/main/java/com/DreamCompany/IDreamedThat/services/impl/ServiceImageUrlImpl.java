package com.DreamCompany.IDreamedThat.services.impl;

import com.DreamCompany.IDreamedThat.repositories.RepositoryImageUrl;
import com.DreamCompany.IDreamedThat.services.ServiceImageUrl;
import org.springframework.stereotype.Service;

@Service
public class ServiceImageUrlImpl implements ServiceImageUrl {
    private final RepositoryImageUrl repositoryImageUrl;

    public ServiceImageUrlImpl(RepositoryImageUrl repositoryImageUrl) {
        this.repositoryImageUrl = repositoryImageUrl;
    }
}
