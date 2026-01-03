package com.cloudacademy.bitcoin;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ConverterSvc {

    private final String BITCOIN_CURRENTPRICE_URL = "https://api.coingecko.com/api/v3/simple/price?ids=bitcoin&vs_currencies=usd,gbp,eur";
    private final HttpGet httpget = new HttpGet(BITCOIN_CURRENTPRICE_URL);

    private CloseableHttpClient httpClient;

    public ConverterSvc (){
        this.httpClient = HttpClients.createDefault();
    }

    public ConverterSvc(CloseableHttpClient httpClient){
        this.httpClient = httpClient;
    }

    public enum Currency {
        USD,
        GBP,
        EUR
    }

    public double getExchangeRate(Currency currency){
        double rate = 0;

        try (CloseableHttpResponse response = this.httpClient.execute(httpget)){
            InputStream inputStream = response.getEntity().getContent();
            var json = new BufferedReader(new InputStreamReader(inputStream));

            @SuppressWarnings("deprecation")
            JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
            rate = jsonObject.getAsJsonObject("bitcoin").get(currency.toString().toLowerCase()).getAsDouble();
        
        } catch (Exception ex) {
            rate = -1;
        }

        return rate;
    }

    public double convertBitcoins(Currency currency, double coins){
        double dollars = 0;

        if(coins < 0){
            throw new IllegalArgumentException("Number of coins must not be less than zero.");
        }

        var exchangeRate = getExchangeRate(currency);

        if(exchangeRate >= 0){
            dollars = exchangeRate * coins;
        } else {
            dollars = -1;
        }

        return dollars;
    }

}