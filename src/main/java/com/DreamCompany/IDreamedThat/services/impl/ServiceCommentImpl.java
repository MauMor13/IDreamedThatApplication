package com.DreamCompany.IDreamedThat.services.impl;

import com.DreamCompany.IDreamedThat.repositories.RepositoryComment;
import com.DreamCompany.IDreamedThat.services.ServiceComment;
import org.springframework.stereotype.Service;

@Service
public class ServiceCommentImpl implements ServiceComment {
    private final RepositoryComment repositoryComment;

    public ServiceCommentImpl(RepositoryComment repositoryComment) {
        this.repositoryComment = repositoryComment;
    }
}
