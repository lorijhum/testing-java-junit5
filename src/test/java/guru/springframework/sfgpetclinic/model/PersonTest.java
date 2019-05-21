package guru.springframework.sfgpetclinic.model;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("model")
class PersonTest {
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