package cybersoft.example.cozastore.filter;


import cybersoft.example.cozastore.utils.JwtHelper;
import io.jsonwebtoken.Claims;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

//tat ca request deu chay vao
@Component
public class JwtFilter extends OncePerRequestFilter {

    /*
    * Nhan duoc token truyen tren header
    * Giai ma token
    * Neu giai ma thanh cong thi hop le
    * Tao chung thuc va cho vao link ng dung request
    * */
    @Autowired
    private JwtHelper jwtHelper;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
     //Lay gia tri cua header c key la Authorization
        try {
            String header = request.getHeader("Authorization");
            if(header.startsWith("Bearer ")) {
                String token = header.substring(7);
//          System.out.println(token);
                Claims claims = jwtHelper.decodeToken(token);
                if(claims != null){
                    //tao chung thuc cho Security
                    SecurityContext context = SecurityContextHolder.getContext();
                    UsernamePasswordAuthenticationToken user =new UsernamePasswordAuthenticationToken("","",new ArrayList<>());
                    context.setAuthentication(user);
                }
            }
        }catch(Exception e)
        {

        }

       filterChain.doFilter(request,response);
    }
}
