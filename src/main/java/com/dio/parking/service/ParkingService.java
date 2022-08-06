package com.dio.parking.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.dio.parking.exception.ParkingNotFoundException;
import com.dio.parking.model.Parking;

@Service
public class ParkingService {

	private static Map<String, Parking> parkingMap = new HashMap<>();

	public List<Parking> findAll() {
		return parkingMap.values().stream().collect(Collectors.toList());
	}

	private static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	public Parking findById(String id) {
		Parking parking = parkingMap.get(id);
		if (parking == null)
			throw new ParkingNotFoundException(id);
		return parking;
	}

	public Parking create(Parking parkingCreate) {
		String uuid = getUUID();
		parkingCreate.setId(uuid);
		parkingCreate.setEntryDate(LocalDateTime.now());
		parkingMap.put(uuid, parkingCreate);
		return parkingCreate;
	}

	public void delete(String id) {
		findById(id);
		parkingMap.remove(id);
	}

	public Parking update(String id, Parking parkingUpdate) {
		Parking parking = findById(id);
		parking.setColor(parkingUpdate.getColor());
		parkingMap.replace(id, parking);
		return parking;
	}

	public Parking exit(String id) {
		// TODO [1] Find the parking, [2] Update the exit date and [3] calculate the bill
		return null;
	}

}
