package guru.springframework.sfgpetclinic;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@TestInstance(PER_CLASS)
@Tag("controllers")
public interface ControllerTests {


    @BeforeAll
    default void beforeAll() {
        System.out.println("This will run before all controller tests");
    }
}
