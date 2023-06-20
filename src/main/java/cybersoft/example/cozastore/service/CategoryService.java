package cybersoft.example.cozastore.service;

import cybersoft.example.cozastore.entity.CategoryEntity;
import cybersoft.example.cozastore.payload.response.CategoryResponse;
import cybersoft.example.cozastore.repository.CategoryRepository;
import cybersoft.example.cozastore.service.imp.CategoryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CategoryService implements CategoryServiceImp {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<CategoryResponse> getAllCategory() {
        List<CategoryEntity> list =categoryRepository.findAll();
//        System.out.println(Arrays.toString(new List[]{list}));
        List<CategoryResponse> responseList=new ArrayList<>();

        for (CategoryEntity data:list) {
            CategoryResponse categoryResponse=new CategoryResponse();
            categoryResponse.setId(data.getId());
            categoryResponse.setName(data.getName());

            responseList.add(categoryResponse);
        }
        return responseList;
    }
}
