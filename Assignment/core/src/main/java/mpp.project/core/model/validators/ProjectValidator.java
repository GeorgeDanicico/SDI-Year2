package mpp.project.core.model.validators;

import mpp.project.core.exceptions.ValidatorException;
import mpp.project.core.model.Project;

import java.util.function.Supplier;
import java.util.stream.Stream;

public class ProjectValidator implements Validator<Project> {

    /**
     * validates a project
     * @param project
     *      project that will be validated
     * @throws ValidatorException
     *      if the project is not valid
     */
    @Override
    public void validate(Project project) throws ValidatorException {
        Supplier<Stream<Project>> singleProjectStreamSupplier = () -> Stream.of(project);

        singleProjectStreamSupplier.get().filter(
                x -> (
                        x.getName() == null
                                || x.getDescription() == null
                                || x.getCreatedBy() == null
                )
        ).findAny().ifPresent(x -> {throw new ValidatorException("Project attributes can't be null");});


        singleProjectStreamSupplier.get().filter(
                x -> (
                        x.getName().isEmpty()
                                || x.getDescription().isEmpty()
                )
        ).findAny().ifPresent(x -> {throw new ValidatorException("Empty data provided to Project");});
    }

}