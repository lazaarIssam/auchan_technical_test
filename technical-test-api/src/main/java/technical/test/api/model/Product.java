package technical.test.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

// defining the collection name
@Document(collection = "products")
// annotation to user getters and setters and other pre-defined methods
@Data
// annotation to define constructors
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    // define the id and other fields
    @Id
    @MongoId(FieldType.INT64)
    private ObjectId _id;
    private String name;
    private String numserie;
    private Integer price;
    private String type;
}
