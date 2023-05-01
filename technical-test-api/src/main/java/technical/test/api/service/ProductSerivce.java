package technical.test.api.service;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import technical.test.api.model.Product;
import technical.test.api.repository.ProductRepo;

import java.util.List;
import java.util.Optional;

@Service
public class ProductSerivce {

    // wire this service class to the repo interface
    @Autowired
    private ProductRepo productRepo;

    // fucntion to list all the products from the db
    public List<Product> getAllProducts (){
        return productRepo.findAll();
    }

    // function to create a product
    public String createProduct (Product product) throws IllegalArgumentException {
        if (product == null || product.getName() == null || product.getName().isEmpty()) {
            // throw error 301 => empty field(s)
            throw new IllegalArgumentException("301");
        }

        try{
            productRepo.save(product);
            return "200";
        }catch (Exception e){
            return "Failed to create product: " + e.getMessage();
        }
    }

    // function to update a product
    public String updateProduct (Product product){
        try {
            Optional<Product> optionalProduct = productRepo.findById(product.get_id());
            //Optional<Product> optionalProduct = productRepo.findProductByNumserie(product.getNumserie());
            if(optionalProduct.isPresent()){
                Product productToUpdate = optionalProduct.get();
                productToUpdate.setName(product.getName());
                productToUpdate.setNumserie(product.getNumserie());
                productToUpdate.setPrice(product.getPrice());
                productToUpdate.setType(product.getType());
                productRepo.save(productToUpdate);
                return "200";
            }
            return "Product no found";
        } catch (Exception e) {
            return "Failed to update product: " + e.getMessage();
        }
    }

    // function to delete a product using the id
    public String deleteProduct (Product product){
        try{
            Optional<Product> optionalProduct = productRepo.findById(product.get_id());
            if(optionalProduct.isPresent()){
                productRepo.deleteById(product.get_id());
                return "200";
            }
            return "Product no found";
        }catch (Exception e){
            return "Product not found "+e.getMessage();
        }
    }
 }
