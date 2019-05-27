package guru.springframework.sfgpetclinic.model;

import guru.springframework.sfgpetclinic.CustomArgsProvider;
import guru.springframework.sfgpetclinic.ModelTests;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

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
    @DisplayName("value source test")
    @ParameterizedTest(name = "{displayName}-[{index}] {arguments}")
    @ValueSource(strings={"Spring", "Framework", "Guru"})
    void testValueSource(String value) {
        System.out.println(value);
    }

    @DisplayName("owner type enum test")
    @ParameterizedTest(name = "{displayName}-[{index}] {arguments}")
    @EnumSource(OwnerType.class)
    void enumTest(OwnerType ownerType) {
        System.out.println(ownerType);
    }

    @DisplayName("CSV input test")
    @ParameterizedTest(name = "{displayName}-[{index}] {arguments}")
    @CsvSource({
            "FL, 1, 1",
            "VA, 2, 2",
            "NJ, 3, 3"
    })
    void csvInputTest(String stateName, int val1, int val2) {
        System.out.println(stateName + ":" + val1 + "," + val2);

    }

    @DisplayName("CSV from file test")
    @ParameterizedTest(name = "{displayName}-[{index}] {arguments}")
    @CsvFileSource(resources = "/input.csv", numLinesToSkip = 1)
    void csvFromFileTest(String stateName, int val1, int val2) {
        System.out.println(stateName + ":" + val1 + "," + val2);

    }

    @DisplayName("Method Provider test")
    @ParameterizedTest(name = "{displayName}-[{index}] {arguments}")
    @MethodSource("getargs")
    void fromMethodTest(String stateName, int val1, int val2) {
        System.out.println(stateName + ":" + val1 + "," + val2);

    }

    static Stream<Arguments> getargs() {
        return Stream.of(Arguments.of("FL", 1, 1),
                Arguments.of("VA",2,2),
                Arguments.of("OH",3,3));
    }

    @DisplayName("Custom Provider test")
    @ParameterizedTest(name = "{displayName}-[{index}] {arguments}")
    @ArgumentsSource(CustomArgsProvider.class)
    void customProviderdTest(String stateName, int val1, int val2) {
        System.out.println(stateName + ":" + val1 + "," + val2);

    }
}