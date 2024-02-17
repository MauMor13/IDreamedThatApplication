package com.DreamCompany.IDreamedThat.services.impl;

import com.DreamCompany.IDreamedThat.DTOs.SocialUserDTO;
import com.DreamCompany.IDreamedThat.DTOs.SocialUserSignupDTO;
import com.DreamCompany.IDreamedThat.awsS3Config.awsS3Services.S3Service;
import com.DreamCompany.IDreamedThat.models.Keystore;
import com.DreamCompany.IDreamedThat.models.SocialUser;
import com.DreamCompany.IDreamedThat.repositories.RepositorySocialUser;
import com.DreamCompany.IDreamedThat.services.ServicePerson;
import com.DreamCompany.IDreamedThat.services.ServiceSocialUser;
import com.DreamCompany.IDreamedThat.services.sendEmail.ServiceSendEmail;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ServiceSocialUserImpl implements ServiceSocialUser {
    private final RepositorySocialUser repositorySocialUser;
    private final ServicePerson servicePerson;
    private final ServiceSendEmail serviceSendEmail;
    private final PasswordEncoder passwordEncoder;
    private final S3Service s3Service;

    public ServiceSocialUserImpl(RepositorySocialUser repositorySocialUser,
                                 ServicePerson servicePerson,
                                 PasswordEncoder passwordEncoder,
                                 ServiceSendEmail serviceSendEmail,
                                 S3Service s3Service) {
        this.repositorySocialUser = repositorySocialUser;
        this.servicePerson = servicePerson;
        this.passwordEncoder = passwordEncoder;
        this.serviceSendEmail = serviceSendEmail;
        this.s3Service = s3Service;
    }

    @Override
    public void save(SocialUser socialUser){ repositorySocialUser.save(socialUser); }
    @Override
    public boolean existsByNickName(String nickname){ return  this.repositorySocialUser.existsByNickName(nickname); }

    @Override
    public SocialUser findByEmail(String email) {
        return this.repositorySocialUser.findByEmail(email);
    }

    @Override
    public SocialUser findById(long id){ return repositorySocialUser.findById(id); };

    @Override
    public boolean emailIsValid(String email) {
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    @Override
    public ResponseEntity<Object> socialUserSignup(SocialUserSignupDTO socialUserSignupDTO) throws MessagingException, UnsupportedEncodingException {
        if (!this.emailIsValid(socialUserSignupDTO.getEmail())){
            return new ResponseEntity<>("Your email is invalid", HttpStatus.BAD_REQUEST);
        }
        if(servicePerson.existsByEmail(socialUserSignupDTO.getEmail())){
            return new ResponseEntity<>("Email already registered", HttpStatus.CONFLICT);
        }
        if(this.existsByNickName(socialUserSignupDTO.getNickName())){
            return new ResponseEntity<>("This nickname is already in use", HttpStatus.CONFLICT);
        }
        SocialUser socialUser = new SocialUser(
                socialUserSignupDTO.getEmail(),
                socialUserSignupDTO.getNickName(),
                passwordEncoder.encode(socialUserSignupDTO.getPassword())
        );
        servicePerson.save(socialUser);
        serviceSendEmail.sendCode(socialUserSignupDTO.getEmail());
        return new ResponseEntity<>("\n" + "Correctly registered, please check the email to confirm",HttpStatus.CREATED);
    }

    @Override
    public void confirmRegistration(String key, HttpServletResponse response) throws IllegalArgumentException{
        String email = Keystore.getterEmail(key);
        if(email != null){
            SocialUser userRegister = this.findByEmail(email);
            userRegister.setActive(true);
            servicePerson.save(userRegister);
            Keystore.clearKey(key);
            //direccion a la cual enviar al usuario luego de verificar email
            response.setHeader("Location", "/direccion.html");
            response.setStatus(HttpServletResponse.SC_FOUND);
        }
        else{
            throw new IllegalArgumentException("incorrect key");
        }
    }

    @Override
    public ResponseEntity<Object> getUserAuth(){
        Authentication authenticationUser = SecurityContextHolder.getContext().getAuthentication();
        if (authenticationUser == null){
            return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
        }

        SocialUser socialUserAuth = this.findByEmail(authenticationUser.getName());
        SocialUserDTO socialUserDTO = new SocialUserDTO(socialUserAuth);
        if (socialUserAuth.getImgAvatarUrl() != null) {
            socialUserDTO.setImageProfile(s3Service.getObject(socialUserAuth.getImgAvatarUrl()));
        }
        return new ResponseEntity<>(socialUserDTO ,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> patchAttribute(String name, String lastName, MultipartFile image, String borderColorImg) throws IOException {

        Authentication authenticationUser = SecurityContextHolder.getContext().getAuthentication();
        if (authenticationUser == null){
            return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
        }
        SocialUser userAuth = this.findByEmail(authenticationUser.getName());

        if (!name.isEmpty())
            userAuth.setName(name);

        if (!lastName.isEmpty())
            userAuth.setLastName(lastName);

        if (!borderColorImg.isEmpty())
            userAuth.setBorderColorImg(borderColorImg);

        if(!image.isEmpty()){
            userAuth.setImgAvatarUrl("imgProfile" + userAuth.getId());
            s3Service.createObject(userAuth.getImgAvatarUrl(), image);
        }

        servicePerson.save(userAuth);

        return new ResponseEntity<>("Modification ok", HttpStatus.OK);
    }
}
