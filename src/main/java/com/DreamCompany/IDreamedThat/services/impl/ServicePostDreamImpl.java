package com.DreamCompany.IDreamedThat.services.impl;

import com.DreamCompany.IDreamedThat.repositories.RepositoryPostDream;
import com.DreamCompany.IDreamedThat.services.ServicePostDream;
import org.springframework.stereotype.Service;

@Service
public class ServicePostDreamImpl implements ServicePostDream {
    private final RepositoryPostDream repositoryPostDream;

    public ServicePostDreamImpl(RepositoryPostDream repositoryPostDream) {
        this.repositoryPostDream = repositoryPostDream;
    }
}
