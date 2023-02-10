package me.dio.parking.service;

import me.dio.parking.exception.ParkingNotFoundException;
import me.dio.parking.model.Parking;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ParkingService {

    private static Map<String, Parking> parkingMap = new HashMap();

    static  {
        var id = getUUID();
        var id1 = getUUID();
        var id2 = getUUID();
        Parking parking = new Parking(id, "DMS-1111", "SC", "CELTA", "PRATA");
        Parking parking1 = new Parking(id1, "ABC-1B34", "SP", "MITSUBISHI OUTLANDER", "PRETA");
        Parking parking2 = new Parking(id2, "DEF-2G56", "SP", "VW BRASILIA", "AMARELA");
        parkingMap.put(id, parking);
        parkingMap.put(id1, parking1);
        parkingMap.put(id2, parking2);
    }

    public List<Parking> findAll() {
        return parkingMap.values().stream().collect(Collectors.toList());
    }

    private static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");

    }

    public Parking findById(String id) {
        Parking parking = parkingMap.get(id);
        if (parking == null) {
            throw new ParkingNotFoundException(id);
        }
        return parking;
    }

    public Parking create(Parking parkingCreate) {
        String uuid = getUUID();
        parkingCreate.setId(getUUID());
        parkingCreate.setEntryDate(LocalDateTime.now());
        parkingMap.put(uuid, parkingCreate);
        return parkingCreate;
    }

}
