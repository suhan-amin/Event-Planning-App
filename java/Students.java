package com.example.myapplication2;

public class Students {
    String service,venue,nof;


    public Students(String service, String venue, String dte, String nof) {
        this.service = service;
        this.venue = venue;
        
        this.nof = nof;
    }

    public Students(String service, String venue, String nof) {
    }

    public String getService() {
        return service;
    }

    public String getVenue() {
        return venue;
    }

    
    public String getNof() {
        return nof;
    }
}
