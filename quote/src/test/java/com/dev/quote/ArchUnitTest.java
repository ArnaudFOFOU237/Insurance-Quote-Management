package com.dev.quote;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.springframework.web.bind.annotation.RestController;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packagesOf = QuoteApplication.class)
public class ArchUnitTest {
    @ArchTest
    public static final ArchRule CONTROLLER_NAMING = classes().that()
            .areAnnotatedWith(RestController.class)
            .or().haveSimpleNameEndingWith("Controller")
            .should().beAnnotatedWith(RestController.class)
            .andShould().haveSimpleNameEndingWith("Controller")
            .because("Le Controller doit être facile à retrouver");

    @ArchTest
    public static final ArchRule DEPENDENCIES_BETWEEN_PACKAGE = classes()
            .that().resideInAPackage("..domaine..")
            .should().onlyBeAccessed().byAnyPackage("..service..", "..domaine..", "..model..", "..adapter..","..test..",
                    "..glue..", "..repository..");
}
