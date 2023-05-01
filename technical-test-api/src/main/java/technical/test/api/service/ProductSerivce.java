package technical.test.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import technical.test.api.model.Product;
import technical.test.api.repository.ProductRepo;


@Service
public class ProductSerivce {

    // wire this service class to the repo interface
    @Autowired
    private ProductRepo productRepo;

    // fucntion to list all the products from the db
    public Flux<Product> getAllProducts (){
        return productRepo.findAll();
    }

    // function to create a product
    public Mono<String> createProduct (Product product) throws IllegalArgumentException {
        if (product == null || product.getName() == null || product.getName().isEmpty()) {
            // throw error 301 => empty field(s)
            throw new IllegalArgumentException("301");
        }

        return productRepo.save(product).thenReturn("200").onErrorResume(e -> Mono.just("Failed to create a product "+e.getMessage()));
    }

    // function to update a product
    public Mono<String> updateProduct (Product product){
        return productRepo.findById(product.get_id()).flatMap(existingProduct ->{
            existingProduct.setName(product.getName());
            existingProduct.setNumserie(product.getNumserie());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setType(product.getType());
            return productRepo.save(existingProduct);
        })
                .map(updatedProduct -> "200")
                .switchIfEmpty(Mono.just("Product not found"))
                .onErrorResume(error -> Mono.just("Failed to update product: " + error.getMessage()));
    }

    // function to delete a product using the id
    public Mono<String> deleteProduct(Product product) {
        return productRepo.findById(product.get_id())
                .flatMap(existingProduct -> productRepo.delete(existingProduct)
                        .thenReturn("200"))
                .switchIfEmpty(Mono.just("Product not found"))
                .onErrorResume(error -> Mono.just("Failed to delete product " + error.getMessage()));
    }
 }
