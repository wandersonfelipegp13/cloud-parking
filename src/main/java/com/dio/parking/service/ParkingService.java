package com.dio.parking.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dio.parking.exception.ParkingNotFoundException;
import com.dio.parking.model.Parking;
import com.dio.parking.repository.ParkingRepository;

@Service
public class ParkingService {

	private final ParkingRepository parkingRepository;

	public ParkingService(ParkingRepository parkingRepository) {
		this.parkingRepository = parkingRepository;
	}

	private static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	@Transactional(readOnly = true)
	public List<Parking> findAll() {
		return parkingRepository.findAll();
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Parking findById(String id) {
		return parkingRepository.findById(id).orElseThrow(() -> new ParkingNotFoundException(id));
	}

	@Transactional
	public Parking create(Parking parkingCreate) {
		String uuid = getUUID();
		parkingCreate.setId(uuid);
		parkingCreate.setEntryDate(LocalDateTime.now());
		parkingRepository.save(parkingCreate);
		return parkingCreate;
	}

	@Transactional
	public void delete(String id) {
		findById(id);
		parkingRepository.deleteById(id);
	}

	@Transactional
	public Parking update(String id, Parking parkingUpdate) {
		Parking parking = findById(id);
		parking.setColor(parkingUpdate.getColor());
		parking.setLicense(parkingUpdate.getLicense());
		parking.setState(parkingUpdate.getState());
		parking.setModel(parkingUpdate.getModel());
		parkingRepository.save(parking);
		return parking;
	}

	@Transactional
	public Parking checkout(String id) {
		Parking parking = findById(id);
		parking.setExitDate(LocalDateTime.now());
		parking.setBill(ParkingCheckout.getBill(parking));
		parkingRepository.save(parking);
		return parking;
	}

}
