package mpp.project.web.controller;

import mpp.project.core.exceptions.InvalidArgumentException;
import mpp.project.core.exceptions.ValidatorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import mpp.project.core.model.Address;
import mpp.project.core.service.AddressService;
import mpp.project.web.converter.AddressConverter;
import mpp.project.web.dto.AddressDto;
import mpp.project.web.dto.AddressesDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @Autowired
    private AddressConverter addressConverter;

    @GetMapping(value = "/")
    ResponseEntity<Map<String, Object>> getAllAddresses(
        @RequestParam(defaultValue = "0") int page
    ) {
        //todo: logs
        try {
            Pageable pageable = PageRequest.of(page, 4);
            Map<String, Object> response = new HashMap<>();

            Page<Address> pageAddresses = addressService.getPagedEntities(pageable);
            List<Address> addresses = pageAddresses.getContent();
            response.put("addresses", addresses);
            response.put("currentPage", pageAddresses.getNumber());
            response.put("totalItems", pageAddresses.getTotalElements());
            response.put("totalPages", pageAddresses.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping( "/")
    ResponseEntity<?> addAddress(@RequestBody AddressDto AddressDto) {
        // TODO: Log parameters
        var address = addressConverter.convertDtoToModel(AddressDto);

        try {
            addressService.addEntity(address);

            // TODO: Log result model
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (InvalidArgumentException | ValidatorException e) {
            return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateAddress(@PathVariable Long id,
                                   @RequestBody AddressDto dto) {

        try {
            addressService.updateEntity(addressConverter.convertDtoToModel(dto));

            // TODO: Log result model
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (InvalidArgumentException | ValidatorException e) {
            return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteAddress(@PathVariable Long id) {
        try {
            addressService.deleteEntity(id);

            // TODO: Log result model
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (InvalidArgumentException | ValidatorException e) {
            return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
        }
    }

}
