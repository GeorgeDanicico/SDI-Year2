package mpp.project.core.service;

import mpp.project.core.exceptions.InvalidArgumentException;
import mpp.project.core.exceptions.ValidatorException;
import mpp.project.core.model.Address;
import mpp.project.core.model.validators.AddressValidator;
import mpp.project.core.repository.AddressRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

    public static final Logger log = LoggerFactory.getLogger(AddressService.class);
    private AddressValidator validator = new AddressValidator();

    private void validateEntity(Address entity) throws ValidatorException {
        validator.validate(entity);
    }

    public void addEntity(Address entity) throws ValidatorException {
        log.info("add address -- method entered");

        this.validateEntity(entity);
        addressRepository.save(entity);

        log.info("add address -- method finished");
    }

    @Transactional
    public void updateEntity(Address entity) throws ValidatorException, InvalidArgumentException {
        log.info("update address -- method entered");

        this.validateEntity(entity);
        Address updateAddress = addressRepository.findById(entity.getId()).orElseThrow(() -> new InvalidArgumentException("Invalid ID"));
        updateAddress.setCity(entity.getCity());
        updateAddress.setStreet(entity.getStreet());
        addressRepository.save(updateAddress);

        log.info("update address -- method finished");
    }

    public void deleteEntity(Long id) throws InvalidArgumentException {
        log.info("delete address -- method entered");

        addressRepository.deleteById(id);

        log.info("delete address -- method finished");
    }

    public Page<Address> getPagedEntities(Pageable pageable) {
        log.info("get paged addresss -- method entered, page={}", pageable.getPageNumber());

        Page<Address> addresses = addressRepository.findAll(pageable);

        log.info("get paged addresss: result={}", addresses.getContent());

        return addresses;
    }
}
