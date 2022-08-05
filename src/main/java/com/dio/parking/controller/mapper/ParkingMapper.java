package com.dio.parking.controller.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.dio.parking.controller.dto.ParkingCreateDTO;
import com.dio.parking.controller.dto.ParkingDTO;
import com.dio.parking.model.Parking;

@Component
public class ParkingMapper {

	private static final ModelMapper MODEL_MAPPER = new ModelMapper();

	public ParkingDTO toParkingDTO(Parking parking) {
		return MODEL_MAPPER.map(parking, ParkingDTO.class);
	}

	public List<ParkingDTO> toParkingDTOList(List<Parking> parkingList) {
		return parkingList.stream().map(this::toParkingDTO).collect(Collectors.toList());
	}

	public Parking toParking(ParkingDTO parkingDTO) {
		return MODEL_MAPPER.map(parkingDTO, Parking.class);
	}

	public Parking toParkingCreate(ParkingCreateDTO parkingCreateDTO) {
		return MODEL_MAPPER.map(parkingCreateDTO, Parking.class);
	}

}
