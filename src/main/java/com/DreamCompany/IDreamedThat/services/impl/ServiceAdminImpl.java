package com.DreamCompany.IDreamedThat.services.impl;

import com.DreamCompany.IDreamedThat.repositories.RepositoryAdmin;
import com.DreamCompany.IDreamedThat.services.ServiceAdmin;
import org.springframework.stereotype.Service;

@Service
public class ServiceAdminImpl implements ServiceAdmin {
    private final RepositoryAdmin repositoryAdmin;

    public ServiceAdminImpl(RepositoryAdmin repositoryAdmin) {
        this.repositoryAdmin = repositoryAdmin;
    }


}
