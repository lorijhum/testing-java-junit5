package guru.springframework.sfgpetclinic.model;

import guru.springframework.sfgpetclinic.ModelTests;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;


class OwnerTest implements ModelTests {

    @Test
    void dependentAssertions() {
        Owner owner = new Owner(1L,"Joe", "Harris");
        owner.setCity("Woodbridge");
        owner.setTelephone("1234567890");

        assertAll("Properties test",
                ()-> assertAll("Person properties",
                    ()-> assertEquals("Joe", owner.getFirstName()),
                    ()-> assertEquals("Harris", owner.getLastName())),
                ()-> assertAll("Owner properties",
                    ()-> assertEquals("Woodbridge", owner.getCity()),
                    ()-> assertEquals("1234567890", owner.getTelephone())
         ));
         //this assertThat is from hamcrest
         assertThat(owner.getCity(),is("Woodbridge"));


    }
}