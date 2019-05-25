package guru.springframework.sfgpetclinic.model;

import guru.springframework.sfgpetclinic.ModelTests;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;


class PersonTest implements ModelTests {
    @Test
    void groupedAssertions() {
        //given
        Person person = new Person(1L,"John", "Smith");
        //then
        assertAll("test props set",
                ()-> assertEquals(person.getFirstName(), "John", "first name fails"),
                ()-> assertEquals(person.getLastName(), "Smith", "last name fails"));



    }
}