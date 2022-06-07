package mpp.project.core.model.validators;

import mpp.project.core.exceptions.ValidatorException;
import mpp.project.core.model.Member;

import java.util.function.Supplier;
import java.util.stream.Stream;

public class MemberValidator implements Validator<Member> {

    /**
     * validates a member
     * @param member
     *      member that will be validated
     * @throws ValidatorException
     *      if the member is not valid
     */
    @Override
    public void validate(Member member) throws ValidatorException {

        Supplier<Stream<Member>> singleMemberStreamSupplier = () -> Stream.of(member);

        singleMemberStreamSupplier.get().filter(
                x -> (
                        x.getName() == null
                                || x.getDateOfBirth() == null
                )
        ).findAny().ifPresent(x -> {throw new ValidatorException("Member attributes can't be null");});


        singleMemberStreamSupplier.get().filter(
                x -> (
                        x.getName().isEmpty()
                )
        ).findAny().ifPresent(x -> {throw new ValidatorException("Empty data provided to Member");});
    }

}