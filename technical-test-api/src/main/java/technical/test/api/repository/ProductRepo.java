package technical.test.api.repository;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import technical.test.api.model.Product;

import java.util.Optional;

// interface that's connected to spring boot mongo to performe crud and more
@Repository
public interface ProductRepo extends MongoRepository<Product, ObjectId> {

    Optional<Product> findProductByNumserie (String numserie);

}
