package me.manhpd.servicelocatorfactorybeanusage.web;

import me.manhpd.servicelocatorfactorybeanusage.dto.TourAdviser;
import me.manhpd.servicelocatorfactorybeanusage.service.TourRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class TourController {

    private static Logger log = LoggerFactory.getLogger(TourController.class);

    @Autowired
    private TourRegistry registry;

    @PostMapping("/payment")
    public String paymentAmount(@RequestBody TourAdviser request) {
        log.info("Getting payment amount of a tour to {}", request.getPlaceOfVisit());
        return registry.getServiceBean(request.getPlaceOfVisit())
                .place(request);
    }
}
