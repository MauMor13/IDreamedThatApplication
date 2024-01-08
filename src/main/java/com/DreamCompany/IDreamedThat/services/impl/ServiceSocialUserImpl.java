package com.DreamCompany.IDreamedThat.services.impl;

import com.DreamCompany.IDreamedThat.repositories.RepositorySocialUser;
import com.DreamCompany.IDreamedThat.services.ServiceSocialUser;
import org.springframework.stereotype.Service;

@Service
public class ServiceSocialUserImpl implements ServiceSocialUser {
    private final RepositorySocialUser repositorySocialUser;

    public ServiceSocialUserImpl(RepositorySocialUser repositorySocialUser) {
        this.repositorySocialUser = repositorySocialUser;
    }
    @Override
    public boolean existsByNickName(String nickname){ return  this.repositorySocialUser.existsByNickName(nickname); }
}
