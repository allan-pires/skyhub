import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by allan on 09/04/17.
 */
public class URLConsumerTest {

    @Test
    public void consumeReturnsJson() throws Exception {
        JSONObject json = URLConsumer.consume("http://54.152.221.29/images.json");

        Assert.assertNotNull(json);
        Assert.assertTrue(json.has("images"));
    }


    @Test
    public void consumeReturnsEmptyJson() throws Exception{
        JSONObject json = URLConsumer.consume("Some bad url");

        Assert.assertNotNull(json);
        Assert.assertEquals(0, json.length());
    }
}