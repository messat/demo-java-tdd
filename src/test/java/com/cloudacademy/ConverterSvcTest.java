package com.cloudacademy.bitcoin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;

import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;

import static org.mockito.Mockito.*;

import java.beans.Transient;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ConverterSvcTest {

    private CloseableHttpClient client;
    private CloseableHttpResponse response;
    private StatusLine statusLine;
    private HttpEntity entity;
    private InputStream stream;

    @BeforeEach
    public void setUp(){
        client = mock(CloseableHttpClient.class);
        response = mock(CloseableHttpResponse.class);
        statusLine = mock(StatusLine.class);
        entity = mock(HttpEntity.class);

        stream = new ByteArrayInputStream("{\"bitcoin\":{\"usd\":90052,\"gbp\":66869,\"eur\":76787}}".getBytes());
    }

    @Test
    public void getExchangeRate_USD_ReturnsUSDExchangeRate() throws IOException {
       //arrange
       when(statusLine.getStatusCode()).thenReturn(200);
       when(response.getStatusLine()).thenReturn(statusLine);
       when(response.getEntity()).thenReturn(entity);
       when(entity.getContent()).thenReturn(stream);
       when(client.execute(any(HttpGet.class))).thenReturn(response);


       //act
       ConverterSvc converterSvc = new ConverterSvc(client);
       var actual = converterSvc.getExchangeRate(ConverterSvc.Currency.USD);

       //assert
       double expected = 90052;
       assertEquals(expected, actual);

    }

   @Test
    public void getExchangeRate_GBP_ReturnsGBPExchangeRate() throws IOException {
        //arrange
        when(statusLine.getStatusCode()).thenReturn(200);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);
        when(entity.getContent()).thenReturn(stream);
        when(client.execute(any(HttpGet.class))).thenReturn(response);
    
        //act
        ConverterSvc converterSvc = new ConverterSvc(client);
        var actual = converterSvc.getExchangeRate(ConverterSvc.Currency.GBP);
    
        //assert
        double expected = 66869;
        assertEquals(expected, actual);
    }

    @Test
    public void getExchangeRate_EUR_ReturnsEURExchangeRate() throws IOException {
        //arrange
        when(statusLine.getStatusCode()).thenReturn(200);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);
        when(entity.getContent()).thenReturn(stream);
        when(client.execute(any(HttpGet.class))).thenReturn(response);
    
        //act
        ConverterSvc converterSvc = new ConverterSvc(client);
        var actual = converterSvc.getExchangeRate(ConverterSvc.Currency.EUR);
    
        //assert
        double expected = 76787;
        assertEquals(expected, actual);
    }

    @Test
    public void convertBitcoins_1BitCoinToUSD_ReturnsUSDDollars() throws IOException {
        //arrange
        when(statusLine.getStatusCode()).thenReturn(200);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);
        when(entity.getContent()).thenReturn(stream);
        when(client.execute(any(HttpGet.class))).thenReturn(response);
    
        //act
        ConverterSvc converterSvc = new ConverterSvc(client);
        var actual = converterSvc.convertBitcoins(ConverterSvc.Currency.USD, 1);
    
        //assert
        double expected = 90052;
        assertEquals(expected, actual);
    }
    
    @Test
    public void convertBitcoins_2BitCoinToUSD_ReturnsUSDDollars() throws IOException {
        //arrange
        when(statusLine.getStatusCode()).thenReturn(200);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);
        when(entity.getContent()).thenReturn(stream);
        when(client.execute(any(HttpGet.class))).thenReturn(response);

        //act
        ConverterSvc converterSvc = new ConverterSvc(client);
        var actual = converterSvc.convertBitcoins(ConverterSvc.Currency.USD, 2);
    
        //assert
        double expected = 180104;
        assertEquals(expected, actual);
    }

    @Test
    public void convertBitcoins_0BitCoinToUSD_ReturnsZeroUSDDollars() throws IOException {
        //arrange
        when(statusLine.getStatusCode()).thenReturn(200);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);
        when(entity.getContent()).thenReturn(stream);
        when(client.execute(any(HttpGet.class))).thenReturn(response);
    
        //act
        ConverterSvc converterSvc = new ConverterSvc(client);
        var actual = converterSvc.convertBitcoins(ConverterSvc.Currency.USD, 0);
    
        //assert
        double expected = 0;
        assertEquals(expected, actual);
    }

    @Test
    public void convertBitcoins_1BitCoinToGBP_ReturnsGBPDollars() throws IOException {
        //arrange
        when(statusLine.getStatusCode()).thenReturn(200);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);
        when(entity.getContent()).thenReturn(stream);
        when(client.execute(any(HttpGet.class))).thenReturn(response);
    
        //act
        ConverterSvc converterSvc = new ConverterSvc(client);
        var actual = converterSvc.convertBitcoins(ConverterSvc.Currency.GBP, 1);
    
        //assert
        double expected = 66869;
        assertEquals(expected, actual);
    }
    
    @Test
    public void convertBitcoins_2BitCoinToGBP_ReturnsGBPDollars() throws IOException {
        //arrange
        when(statusLine.getStatusCode()).thenReturn(200);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);
        when(entity.getContent()).thenReturn(stream);
        when(client.execute(any(HttpGet.class))).thenReturn(response);
    
        //act
        ConverterSvc converterSvc = new ConverterSvc(client);
        var actual = converterSvc.convertBitcoins(ConverterSvc.Currency.GBP, 2);
    
        //assert
        double expected = 133738;
        assertEquals(expected, actual);
    }

    @Test
    public void convertBitcoins_0BitCoinToGBP_ReturnsZeroGBPDollars() throws IOException {
        //arrange
        when(statusLine.getStatusCode()).thenReturn(200);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);
        when(entity.getContent()).thenReturn(stream);
        when(client.execute(any(HttpGet.class))).thenReturn(response);
    
        //act
        ConverterSvc converterSvc = new ConverterSvc(client);
        var actual = converterSvc.convertBitcoins(ConverterSvc.Currency.GBP, 0);
    
        //assert
        double expected = 0;
        assertEquals(expected, actual);
    }

    @Test
    public void convertBitcoins_1BitCoinToEUR_ReturnsEURDollars() throws IOException {
        //arrange
        when(statusLine.getStatusCode()).thenReturn(200);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);
        when(entity.getContent()).thenReturn(stream);
        when(client.execute(any(HttpGet.class))).thenReturn(response);
    
        //act
        ConverterSvc converterSvc = new ConverterSvc(client);
        var actual = converterSvc.convertBitcoins(ConverterSvc.Currency.EUR, 1);
    
        //assert
        double expected = 76787;
        assertEquals(expected, actual);
    }
    
    @Test
    public void convertBitcoins_2BitCoinToEUR_ReturnsEURDollars() throws IOException {
        //arrange
        when(statusLine.getStatusCode()).thenReturn(200);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);
        when(entity.getContent()).thenReturn(stream);
        when(client.execute(any(HttpGet.class))).thenReturn(response);
    
        //act
        ConverterSvc converterSvc = new ConverterSvc(client);
        var actual = converterSvc.convertBitcoins(ConverterSvc.Currency.EUR, 2);
    
        //assert
        double expected = 153574;
        assertEquals(expected, actual);
    }

    @Test
    public void getExchangeRate_ClosingResponseThrowsIOException_ReturnsCorrectErrorCode() throws IOException {
        //arrange
        when(statusLine.getStatusCode()).thenReturn(200);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);
        when(entity.getContent()).thenReturn(stream);
        when(client.execute(any(HttpGet.class))).thenReturn(response);

        doThrow(IOException.class).when(response).close();

        //act
        ConverterSvc converterSvc = new ConverterSvc(client);
        var actual = converterSvc.getExchangeRate(ConverterSvc.Currency.USD);

        //assert
        double expected = -1;
        assertEquals(expected, actual);
    }

    @Test
    public void convertBitcoins_0BitCoinToEUR_ReturnsZeroEURDollars() throws IOException {
        //arrange
        when(statusLine.getStatusCode()).thenReturn(200);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);
        when(entity.getContent()).thenReturn(stream);
        when(client.execute(any(HttpGet.class))).thenReturn(response);
    
        //act
        ConverterSvc converterSvc = new ConverterSvc(client);
        var actual = converterSvc.convertBitcoins(ConverterSvc.Currency.EUR, 0);
    
        //assert
        double expected = 0;
        assertEquals(expected, actual);
    }

    @Test
    public void convertBitcoins_NegativeBitcoinsToUSD_ThrowsException() throws IOException {
           //arrange
        when(statusLine.getStatusCode()).thenReturn(200);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);
        when(entity.getContent()).thenReturn(stream);
        when(client.execute(any(HttpGet.class))).thenReturn(response);

          //act
        ConverterSvc converterSvc = new ConverterSvc(client);
        assertThrows(IllegalArgumentException.class, () -> converterSvc.convertBitcoins(ConverterSvc.Currency.USD, -1));
    }

    
    @Test
    public void convertBitcoins_503ServiceUnavailable_ReturnsCorrectErrorCode() throws IOException {
           //arrange
        when(statusLine.getStatusCode()).thenReturn(503);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(client.execute(any(HttpGet.class))).thenReturn(response);

          //act
        ConverterSvc converterSvc = new ConverterSvc(client);
        var actual = converterSvc.convertBitcoins(ConverterSvc.Currency.USD, 2);

        //assert
        double expected = -1;
        assertEquals(expected, actual);
    }


}