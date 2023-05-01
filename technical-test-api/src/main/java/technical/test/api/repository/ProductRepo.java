package technical.test.api.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import technical.test.api.model.Product;

import java.util.Optional;

// interface that's connected to spring boot mongo to performe crud and more
@Repository
public interface ProductRepo extends ReactiveMongoRepository<Product, ObjectId> {

    Optional<Product> findProductByNumserie (String numserie);

}
