package com.manhpd.poiWord;

import com.manhpd.dto.LstParameter;

import java.util.concurrent.BlockingDeque;

public class PoiWordConsumer {

    private BlockingDeque<LstParameter> data;

    public PoiWordConsumer(BlockingDeque<LstParameter> data) {
        this.data = data;
    }

    public void process() {

    }

}
