import com.skyhub.consumer.URLConsumer;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by allan on 09/04/17.
 */
public class URLConsumerTest {

    @Test
    public void consumeReturnsText() throws Exception {
        String text = URLConsumer.getText("http://54.152.221.29/images.json");

        Assert.assertNotNull(text);
        Assert.assertTrue(text.contains("images"));
    }


    @Test
    public void consumeReturnsEmptyString() throws Exception{
        String text = URLConsumer.getText("Some bad url");

        Assert.assertNotNull(text);
        Assert.assertEquals("", text);
    }
}