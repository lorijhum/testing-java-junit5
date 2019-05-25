package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.ControllerTests;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;

import java.time.Duration;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeFalse;
import static org.junit.jupiter.api.Assumptions.assumeTrue;


class IndexControllerTest implements ControllerTests {

    private IndexController controller;

    @BeforeEach
    void setUp() {
        controller = new IndexController();
    }

    @DisplayName("Test correct view name is returned from controller")
    @Test
    void index() {
        assertEquals("index", controller.index());
        //can put out a message when test fails
        assertEquals("index", controller.index(), "wrong message returned");
        // this assertion is from assertj
        assertThat(controller.index().startsWith("w"));

    }

    @Test
    @DisplayName("Test exception")
    void oupsHandler() {
        //the lambda is only used if the test fails
       // assertTrue("notimplemented".equals(controller.oupsHandler()), ()-> "some message in lambda expression");
        // we changed the method to throw an exception rather than a string of not implemented, so we are changing the test
        assertThrows(ValueNotFoundException.class, ()-> {
            controller.oupsHandler();
        });
    }

    @Disabled
    @Test
    void testTimeOut() {

        assertTimeout(Duration.ofMillis(3000), () ->  {
            Thread.sleep(2000);
            System.out.println("I got here");
        });
    }
    @Disabled
    @Test
    void testTimeOutPrempt() {

        assertTimeoutPreemptively(Duration.ofMillis(3000), () ->  {
            Thread.sleep(2000);
            System.out.println("I got here 2");
        });
    }

    @Test
    void testAssumptionTrue() {
       //assume different from assert usually used to check for environment running in
        assumeTrue("Lucy".equalsIgnoreCase("LUCY"));
    }

    @Test
    void testAssumptionFalse() {
        //assume different from assert usually used to check for environment running in
        assumeFalse("Development".equalsIgnoreCase(System.getenv("Development")));
    }

    @EnabledOnOs(OS.MAC)
    @Test
    void testMeOnMacOS() {
    }

    @EnabledOnOs(OS.WINDOWS)
    @Test
    void testMeOnWindows() {
    }

    @EnabledOnJre(JRE.JAVA_8)
    @Test
    void testMeOnJava8() {

    }

    @EnabledOnJre(JRE.JAVA_11)
    @Test
    void testMeOnJava11() {
    }

    @EnabledIfEnvironmentVariable(named = "USER", matches = "lhema")
    @Test
    void testIfUserLH() {
    }
}