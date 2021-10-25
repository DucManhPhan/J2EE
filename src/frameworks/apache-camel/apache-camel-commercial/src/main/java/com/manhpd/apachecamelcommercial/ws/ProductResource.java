package com.manhpd.apachecamelcommercial.ws;

import com.manhpd.apachecamelcommercial.model.Product;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductResource {

    @Autowired
    private ProducerTemplate producerTemplate;

    @GetMapping("/products/{category}")
    @ResponseBody
    public List<Product> getProductsByCategory(@PathVariable("category")
                                               final String category) {
        this.producerTemplate.start();
        List<Product> products = this.producerTemplate
                        .requestBody("direct:fetchProducts", category, List.class);
        this.producerTemplate.stop();
        return products;
    }

}
