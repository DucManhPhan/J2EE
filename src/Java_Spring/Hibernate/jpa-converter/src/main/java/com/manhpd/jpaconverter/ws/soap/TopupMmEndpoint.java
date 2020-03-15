package com.manhpd.jpaconverter.ws.soap;


import com.manhpd.jpaconverter.domain.dto.TopupPaymentDto;
import com.manhpd.jpaconverter.domain.repository.ITransactionTopupMMRepository;
import com.manhpd.jpaconverter.shared.http.ResponseTopupMm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class TopupMmEndpoint {

    private static final String NAMESPACE_URI = "http://webservice.payapp.v2.bankplus.viettel.com/";

    @Autowired
    private ITransactionTopupMMRepository transactionTopupMMRepository;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllTransactionTopupMm")
    @ResponsePayload
    public ResponseTopupMm getAllTransactionTopupMm(@RequestPayload String cmd,
                                                    @RequestPayload TopupPaymentDto data,
                                                    @RequestPayload String signature) {
        return new ResponseTopupMm("450", "Hello world!!!");
    }

}
