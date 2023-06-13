package cybersoft.example.cozastore.controller;

import cybersoft.example.cozastore.payload.request.SignupRequest;
import cybersoft.example.cozastore.payload.response.BaseResponse;
import cybersoft.example.cozastore.service.imp.UserServiceImp;
import cybersoft.example.cozastore.utils.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class LoginControlller {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtHelper jwtHelper;
    /*
    * statusCode: 200
    * message:""
    * data:*/
    @Autowired
    private UserServiceImp userServiceImp;
    @RequestMapping(value = "/signin",method= RequestMethod.POST)
    public ResponseEntity<?>signin(
            @RequestParam String username,
            @RequestParam String password
    ){
        UsernamePasswordAuthenticationToken token=
                new UsernamePasswordAuthenticationToken(username,password);
        authenticationManager.authenticate(token);
        String jwt= jwtHelper.generateToken(username);
        //chung thuc thanh cong se chay code sau,that bai se loi chugn thuc
        BaseResponse response =new BaseResponse();
        response.setStatusCode(200);
        response.setData(jwt);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/signup",method= RequestMethod.POST)
    public ResponseEntity<?>signup(
            @Valid SignupRequest request
            ){
        boolean isSuccess = userServiceImp.addUser(request);

        BaseResponse response =new BaseResponse();
        response.setStatusCode(200);
        response.setData(isSuccess);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
