package guru.springframework.sfgpetclinic.model;

import guru.springframework.sfgpetclinic.ModelRepeatedTests;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.TestInfo;

class PersonRepeatedTests implements ModelRepeatedTests {

    @RepeatedTest(value=10, name= "{displayName} : {currentRepetition} - {totalRepetitions}")
    @DisplayName("my repeated test")
    void repeatedTest() {
        //to do write test we want to repeat.  display name gets put in repeatedTest display name,
        //currentRepetition is put in that placeholder and totalRepetition is put in that placeholder
        //so we get output of my repeated test: 1-10
    }

    @RepeatedTest(value=10, name= "{displayName} : {currentRepetition} - {totalRepetitions}")
    @DisplayName("repeated test with dependency injection")
    void repeatedTestWithDI(TestInfo testInfo, RepetitionInfo repetitionInfo) {

    }

    @RepeatedTest(value=2, name= "{displayName} : {currentRepetition} - {totalRepetitions}")
    @DisplayName("new repeated test with dependency injection")
    void newRepeatedTestWithDI(TestInfo testInfo, RepetitionInfo repetitionInfo) {
        //create test
    }
}

