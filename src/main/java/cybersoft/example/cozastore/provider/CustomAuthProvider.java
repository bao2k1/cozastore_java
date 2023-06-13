package cybersoft.example.cozastore.provider;



import cybersoft.example.cozastore.entity.UserEntity;
import cybersoft.example.cozastore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomAuthProvider implements AuthenticationProvider {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
      //Lay username nguoi dung truyen vao
        String username= authentication.getName();
        //Lay password nguoi dung truyen vao
        String password =authentication.getCredentials().toString();

       UserEntity user= userRepository.findByEmail(username);
      if(user !=null&&passwordEncoder.matches(password,user.getPassword()))
      {
        return new UsernamePasswordAuthenticationToken(username,user.getPassword(),new ArrayList<>());
      }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
            //khai bao loai chung thuc su dung de so sanh
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }



}
