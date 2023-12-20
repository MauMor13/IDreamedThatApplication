package com.DreamCompany.IDreamedThat.services.impl;

import com.DreamCompany.IDreamedThat.repositories.RepositoryUser;
import com.DreamCompany.IDreamedThat.services.ServiceUser;
import org.springframework.stereotype.Service;

@Service
public class ServiceUserImpl implements ServiceUser {
    private final RepositoryUser repositoryUser;

    public ServiceUserImpl(RepositoryUser repositoryUser) {
        this.repositoryUser = repositoryUser;
    }
}
