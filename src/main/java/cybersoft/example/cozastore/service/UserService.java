package cybersoft.example.cozastore.service;

import cybersoft.example.cozastore.entity.UserEntity;
import cybersoft.example.cozastore.payload.request.SignupRequest;
import cybersoft.example.cozastore.repository.UserRepository;
import cybersoft.example.cozastore.service.imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserServiceImp {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public boolean addUser(SignupRequest request) {
        boolean isSuccess =false;
        try {
            UserEntity user = new UserEntity();
            user.setUsername(request.getUsername());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setEmail(request.getEmail());
            userRepository.save(user);
            isSuccess=true;
        }catch (Exception e)
        {

        }
return isSuccess;
    }



}
