package com.dio.parking.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.dio.parking.model.Parking;

@Service
public class ParkingService {

	private static Map<String, Parking> parkingMap = new HashMap<>();

	static {
		var id = getUUID();
		Parking parking = new Parking(id, "DMS-1111", "SC", "CELTA", "PRETO");
		parkingMap.put(id, parking);
		id = getUUID();
		parking = new Parking(id, "WAS-1234", "SP", "VW GOL", "BRANCO");
		parkingMap.put(id, parking);
	}

	public List<Parking> findAll() {
		return parkingMap.values().stream().collect(Collectors.toList());
	}

	private static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	public Parking findById(String id) {
		return parkingMap.get(id);
	}

	public Parking create(Parking parkingCreate) {
		String uuid = getUUID();
		parkingCreate.setId(uuid);
		parkingCreate.setEntryDate(LocalDateTime.now());
		parkingMap.put(uuid, parkingCreate);
		return parkingCreate;
	}

}
