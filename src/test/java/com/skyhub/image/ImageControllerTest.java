package com.skyhub.image;

import com.skyhub.connection.URLConsumer;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static com.skyhub.fixtures.ImagesJsonFixture.defaultJsonText;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Created by allan on 10/04/17.
 */
public class ImageControllerTest {

    @Mock
    private ImageRepository repository;

    @Mock
    private URLConsumer consumer;

    @Mock
    private ImageScale scale;

    @InjectMocks
    private ImageController controller;


    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void listImagesCreatesJSON() throws Exception {
        when(repository.getAllImagesNames()).thenReturn(new ArrayList<>());

        String result = controller.listImages();
        JSONObject obj = new JSONObject(result);

        Assert.assertNotNull(obj);
    }

    @Test
    public void initializeImageRepository() throws Exception {
        BufferedImage mockImage = Mockito.mock(BufferedImage.class);

        when(consumer.getTextFromURL(anyString())).thenReturn(defaultJsonText());
        when(scale.toSmall(any())).thenReturn(mockImage);
        when(scale.toMedium(any())).thenReturn(mockImage);
        when(scale.toLarge(any())).thenReturn(mockImage);

        doNothing().when(repository).save(any(BufferedImage.class), anyString());

        controller.saveImagesFromURL();

        verify(repository, times(1)).save(any(), eq("img0_large"));
        verify(repository, times(1)).save(any(), eq("img0_medium"));
        verify(repository, times(1)).save(any(), eq("img0_small"));

        verify(repository, times(1)).save(any(), eq("img1_large"));
        verify(repository, times(1)).save(any(), eq("img1_medium"));
        verify(repository, times(1)).save(any(), eq("img1_small"));
    }

}