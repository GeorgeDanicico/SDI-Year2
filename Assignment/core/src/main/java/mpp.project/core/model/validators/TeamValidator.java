package mpp.project.core.model.validators;

import mpp.project.core.model.Team;
import mpp.project.core.exceptions.ValidatorException;

import java.util.function.Supplier;
import java.util.stream.Stream;

public class TeamValidator implements Validator<Team> {

    /**
     * validates a team
     * @param team
     *      team that will be validated
     * @throws ValidatorException
     *      if the team is not valid
     */
    @Override
    public void validate(Team team) throws ValidatorException {
        Supplier<Stream<Team>> singleTeamStreamSupplier = () -> Stream.of(team);

        singleTeamStreamSupplier.get().filter(
                x -> (
                        x.getName() == null
                )
        ).findAny().ifPresent(x -> {throw new ValidatorException("Team attributes can't be null");});


        singleTeamStreamSupplier.get().filter(
                x -> x.getName().isEmpty()
        ).findAny().ifPresent(x -> {throw new ValidatorException("Empty data provided to Team");});
    }

}