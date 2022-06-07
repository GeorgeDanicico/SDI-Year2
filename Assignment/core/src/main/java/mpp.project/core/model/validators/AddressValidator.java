package mpp.project.core.model.validators;

import mpp.project.core.exceptions.ValidatorException;
import mpp.project.core.model.Address;
import mpp.project.core.model.Member;

import java.util.function.Supplier;
import java.util.stream.Stream;

public class AddressValidator implements Validator<Address>{
    @Override
    public void validate(Address entity) throws ValidatorException {
        Supplier<Stream<Address>> addressStreamSupplier = () -> Stream.of(entity);

        addressStreamSupplier.get().filter(
                x -> (
                        x.getCity() == null
                                || x.getStreet() == null
                )
        ).findAny().ifPresent(x -> {throw new ValidatorException("Address attributes can't be null");});

    }

}

