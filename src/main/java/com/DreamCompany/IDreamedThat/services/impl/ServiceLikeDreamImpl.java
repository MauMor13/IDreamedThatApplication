package com.DreamCompany.IDreamedThat.services.impl;

import com.DreamCompany.IDreamedThat.repositories.RepositoryLikeDream;
import com.DreamCompany.IDreamedThat.services.ServiceLikeDream;
import org.springframework.stereotype.Service;

@Service
public class ServiceLikeDreamImpl implements ServiceLikeDream {
    private final RepositoryLikeDream repositoryLikeDream;

    public ServiceLikeDreamImpl(RepositoryLikeDream repositoryLikeDream) {
        this.repositoryLikeDream = repositoryLikeDream;
    }
}
