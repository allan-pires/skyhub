import com.mongodb.DB;
import com.mongodb.MongoClient;
import org.json.JSONObject;

/**
 * Created by allan on 09/04/17.
 */
public class Image {

    public DB getConnection(){
        MongoClient mongoClient = new MongoClient("localhost" , 27017);

        return mongoClient.getDB("mydb");
    }

    public void save(JSONObject json){
        getConnection().command("");
    }

}
