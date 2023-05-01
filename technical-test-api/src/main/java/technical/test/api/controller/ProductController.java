package technical.test.api.controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import technical.test.api.model.Product;
import technical.test.api.service.ProductSerivce;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductSerivce productSerivce;

    @GetMapping("allProducts")
    public ResponseEntity<Flux<Product>> allProducts(){
        Flux<Product> products = productSerivce.getAllProducts();
        if (products == null) {
            // i define 302 as the code result for empty list
            return new ResponseEntity("302",HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<Flux<Product>>(products,HttpStatus.OK);
        }
    }

    @PostMapping("createProduct")
    public ResponseEntity<Mono<String>> createProduct (@RequestBody Product product){
        try{
            Mono<String> result = productSerivce.createProduct(product);
            return new ResponseEntity<>(result,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(Mono.just(e.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("updateProduct")
    public ResponseEntity<Mono<String>> updateProduct (@RequestBody Product product){
        try{
            Mono<String> value = productSerivce.updateProduct(product);
            return new ResponseEntity<>(value,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(Mono.just(e.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("deleteProduct")
    public ResponseEntity<Mono<String>> deleteProduct(@RequestBody Product product){
        try{
            Mono<String> value = productSerivce.deleteProduct(product);
            return new ResponseEntity<>(value,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(Mono.just(e.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }
}
