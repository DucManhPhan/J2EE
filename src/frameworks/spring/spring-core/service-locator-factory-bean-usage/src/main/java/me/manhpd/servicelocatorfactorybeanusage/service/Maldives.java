package me.manhpd.servicelocatorfactorybeanusage.service;

import me.manhpd.servicelocatorfactorybeanusage.dto.TourAdviser;
import org.springframework.stereotype.Service;

@Service("Maldives")
public class Maldives implements TourService {

    @Override
    public String place(TourAdviser request) {
        int amount = request.getNoOfPeople();
        return "Amount for travelling to " + request.getPlaceOfVisit() +
                " is " + amount * 60000;
    }
}
