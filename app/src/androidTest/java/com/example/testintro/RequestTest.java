package com.example.testintro;

import com.example.testintro.network.RequestParser;
import com.example.testintro.network.RequestPerformer;

import org.apache.http.HttpStatus;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RequestTest {

    @Test
    public void testRequestParserStatus() {
        RequestPerformer requestPerformer = mock(RequestPerformer.class);
        when(requestPerformer.getStatusCode()).thenReturn(HttpStatus.SC_OK);
        RequestParser requestParser = new RequestParser(requestPerformer);
        assertThat("request parser should have success status", requestParser.isSuccess(), equalTo(true));
    }

}
