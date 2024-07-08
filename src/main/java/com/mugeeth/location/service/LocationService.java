package com.mugeeth.location.service;

import com.mugeeth.location.entities.Location;

import java.util.List;

public interface LocationService {
    //Creating Methods to implement in service class
    Location saveLocation(Location location);
    Location updateLocation(Location location);
    void deleteLocation(Location location);
    Location getLocationById(int id);
    List<Location> getAllLocations();
}
