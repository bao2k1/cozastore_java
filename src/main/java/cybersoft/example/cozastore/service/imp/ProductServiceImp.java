package cybersoft.example.cozastore.service.imp;

import cybersoft.example.cozastore.payload.response.ProductResponse;
import cybersoft.example.cozastore.repository.ProductRepository;

import java.util.List;

public interface ProductServiceImp  {
    List<ProductResponse> getProductByCategory(int id);
}
