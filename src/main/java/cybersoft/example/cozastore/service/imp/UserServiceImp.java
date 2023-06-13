package cybersoft.example.cozastore.service.imp;

import cybersoft.example.cozastore.payload.request.SignupRequest;
import org.springframework.stereotype.Service;

@Service
public interface UserServiceImp {
    boolean addUser(SignupRequest request);
}
