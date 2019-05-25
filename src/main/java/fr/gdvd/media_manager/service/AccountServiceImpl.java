package fr.gdvd.media_manager.service;

import fr.gdvd.media_manager.dao.MediaRoleRepository;
import fr.gdvd.media_manager.dao.MediaUserRepository;
import fr.gdvd.media_manager.entities.MediaRole;
import fr.gdvd.media_manager.entities.MediaUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

//    @Autowired
    private MediaUserRepository mediaUserRepository;
    private MediaRoleRepository mediaRoleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public AccountServiceImpl(MediaUserRepository mediaUserRepository,
                              MediaRoleRepository mediaRoleRepository){
        this.mediaUserRepository = mediaUserRepository;
        this.mediaRoleRepository = mediaRoleRepository;
    }

    @Override
    public MediaUser saveUser(String login, String password, String confirmedPassword) {
        MediaUser user = mediaUserRepository.findByLogin(login).orElse(null);
        if(user!=null) throw new RuntimeException("User already exist");
        if(!password.equals(confirmedPassword))
            throw new RuntimeException("You must confirm your password");
        MediaUser mediaUser = new MediaUser();
        mediaUser.setLogin(login);
        mediaUser.setActive(true);
        mediaUser.setPassword(bCryptPasswordEncoder.encode(password));
        mediaUser.setDateModif(new Date());
        addRoleToUser(mediaUser, "USER");/**/
        mediaUserRepository.save(mediaUser);
        return mediaUser;
    }

    @Override
    public MediaRole save(MediaRole role) {
        return mediaRoleRepository.save(role);
    }

    @Override
    public MediaUser loadUserByUserName(String login) {
        return mediaUserRepository.findByLogin(login).orElse(null);
    }

    @Override
    public void addRoleToUser(MediaUser mediaUser, String roleName) {
        MediaRole mediaRole = mediaRoleRepository.findByRole(roleName);
        mediaUser.getRoles().add(mediaRole);
    }
}
