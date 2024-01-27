package me.manhpd.servicelocatorfactorybeanusage.service;

public interface TourRegistry {
    public TourService getServiceBean(String serviceName);
}
