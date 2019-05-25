package guru.springframework.sfgpetclinic.model;

import guru.springframework.sfgpetclinic.ModelTests;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
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
    @RepeatedTest(value=10, name= "{displayName} : {currentRepetition} - {totalRepetitions}")
    @DisplayName("my repeated test")
    @Test

    void repeatedTest() {
       //to do write test we want to repeat.  display name gets put in repeatedTest display name,
        //currentRepetition is put in that placeholder and totalRepetition is put in that placeholder
        //so we get output of my repeated test: 1-10 
        }



    }
