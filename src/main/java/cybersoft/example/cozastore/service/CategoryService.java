package cybersoft.example.cozastore.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import cybersoft.example.cozastore.entity.CategoryEntity;
import cybersoft.example.cozastore.payload.response.CategoryResponse;
import cybersoft.example.cozastore.repository.CategoryRepository;
import cybersoft.example.cozastore.service.imp.CategoryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CategoryService implements CategoryServiceImp {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
//    @Cacheable("listCategory")
    public List<CategoryResponse> getAllCategory() {
        System.out.println("kiem tra cache cate");
        List<CategoryResponse> responseList=new ArrayList<>();
        if(redisTemplate.hasKey("listCategory")){
            System.out.println("co data tren redis");
//            System.out.println(redisTemplate.keys("listCategory"));
//            neu co thi lay gia tri tren redis
         String data=redisTemplate.opsForValue().get("listCategory").toString();

            Type listType = new TypeToken<ArrayList<CategoryResponse>>(){}.getType();
            responseList = new Gson().fromJson(data, listType);

        }else{
            System.out.println("khong co data tren redis");
            List<CategoryEntity> list =categoryRepository.findAll();
//        System.out.println(Arrays.toString(new List[]{list}));


            for (CategoryEntity data:list) {
                CategoryResponse categoryResponse=new CategoryResponse();
                categoryResponse.setId(data.getId());
                categoryResponse.setName(data.getName());

                responseList.add(categoryResponse);
            }
            Gson gson=new Gson();
            String data=gson.toJson(responseList);

            redisTemplate.opsForValue().set("listCategory",data);
            System.out.println("Data saved to Redis: " + data);
        }


        return responseList;
    }
}
