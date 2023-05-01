package technical.test.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import technical.test.api.model.Product;
import technical.test.api.service.ProductSerivce;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductSerivce productSerivce;

    @GetMapping("allProducts")
    public ResponseEntity<List<Product>> allProducts(){
        List<Product> products = productSerivce.getAllProducts();
        if (products == null || products.isEmpty()) {
            // i define 302 as the code result for empty list
            return new ResponseEntity("302",HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<List<Product>>(productSerivce.getAllProducts(),HttpStatus.OK);
        }
    }

    @PostMapping("createProduct")
    public ResponseEntity<String> createProduct (@RequestBody Product product){
        try{
            productSerivce.createProduct(product);
            return new ResponseEntity("200",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("updateProduct")
    public ResponseEntity<String> updateProduct (@RequestBody Product product){
        try{
            String value = productSerivce.updateProduct(product);
            return new ResponseEntity(value,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("deleteProduct")
    public ResponseEntity<String> deleteProduct(@RequestBody Product product){
        try{
            System.out.println("id delete:" +product.get_id());
            String value = productSerivce.deleteProduct(product);
            return new ResponseEntity(value,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
