package technical.test.api.repository;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import technical.test.api.model.Product;

// interface that's connected to spring boot mongo to performe crud and more
@Repository
public interface ProductRepo extends ReactiveMongoRepository<Product, ObjectId> {

}
