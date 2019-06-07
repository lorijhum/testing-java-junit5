package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.repositories.SpecialtyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SpecialitySDJpaServiceTest {

    @Mock(lenient = true)
    SpecialtyRepository specialtyRepository;

    @InjectMocks
    SpecialitySDJpaService service;

    @Test
    void findById() {
        //given
        Speciality speciality = new Speciality();
        given(specialtyRepository.findById(1L)).willReturn(Optional.of(speciality));

        //when
        Speciality foundSpeciality = service.findById(1L);

        //then
        assertThat(foundSpeciality).isNotNull();
        //the default is 1 time, shown below, then using times,1
      //  then(specialtyRepository).should().findById(anyLong());
        then(specialtyRepository).should(times(1)).findById(anyLong());
        then(specialtyRepository).shouldHaveNoMoreInteractions();

    }


    @Test
    void deleteByObject() {
        //given
        Speciality speciality = new Speciality();
        //when
        service.delete(speciality);

        //using an argument matcher in the verify to check that we deleted an object of the Speciality Class
        //then

        then(specialtyRepository).should().delete(any(Speciality.class));
    }
    @Test
    void deleteById() {
        //given none

        //when
        service.deleteById(1L);
        service.deleteById(1L);
        // the default for the verify below is 1, so without ,times, it verifies the method was called once
        //then
        then(specialtyRepository).should(times(2)).deleteById(1L);
    }

    @Test
    void deleteByIdAtLeast() {
        //given

        //when
        service.deleteById(1L);
        service.deleteById(1L);

        //then
        //checks that the method was called at least one time
        then(specialtyRepository).should(atLeastOnce()).deleteById(1L);
    }

    @Test
    void deleteByIdAtMost() {
        //when
        service.deleteById(1L);
        service.deleteById(1L);
        service.deleteById(1L);

        //then
        //checks that the method was called at most 5 times
        then(specialtyRepository).should(atMost(5)).deleteById(1L);
    }

    @Test
    void deleteByIdNever() {
        //when
        service.deleteById(1L);
        service.deleteById(1L);
        service.deleteById(1L);

        //then
        //checks that the method was called at most 5 times
        then(specialtyRepository).should(atMost(5)).deleteById(1L);
        //checks that it was never called for id 2
        then(specialtyRepository).should(never()).deleteById(2L);
    }

    @Test
    void delete() {
        //when
        service.delete(new Speciality());
        //then
        then(specialtyRepository).should().delete(any(Speciality.class));

    }

    @Test
    void testDoThrow() {
        doThrow(new RuntimeException("boom")).when(specialtyRepository).delete(any());
        assertThrows(RuntimeException.class, () -> specialtyRepository.delete(new Speciality()));
        verify(specialtyRepository).delete(any());
    }

    @Test
    void testFindByIdThrows() {
        given(specialtyRepository.findById(1L)).willThrow(new RuntimeException("boom"));
        assertThrows(RuntimeException.class, () -> service.findById(1L));
        then(specialtyRepository).should().findById(1L);
    }

    @Test
    void testDeleteBDD() {
        //testing a method with a void return type, we use willThrow before the given
        willThrow(new RuntimeException("boom")).given(specialtyRepository).delete(any());

        assertThrows(RuntimeException.class, () -> specialtyRepository.delete(new Speciality()));

        then(specialtyRepository).should().delete(any());
    }

    @Test
    void testSaveLambda() {
        //given
        final String MATCH_ME = "MATCH_ME";
        Speciality speciality = new Speciality();
        speciality.setDescription(MATCH_ME);

        Speciality savedSpecialty = new Speciality();
        savedSpecialty.setId(1L);

        //need mock to only return on match MATCH_ME string
        given(specialtyRepository.save(argThat(argument ->
                argument.getDescription().equals(MATCH_ME)))).willReturn(savedSpecialty);

        //when
        Speciality returnedSpecialty = service.save(speciality);

        //then
        assertThat(returnedSpecialty.getId()).isEqualTo(1L);
    }

    @Test
    void testSaveLambdaNoMatch() {
        //given
        final String MATCH_ME = "MATCH_ME";
        Speciality speciality = new Speciality();
        speciality.setDescription("Not a match");

        Speciality savedSpecialty = new Speciality();
        savedSpecialty.setId(1L);

        //need mock to only return on match MATCH_ME string
        //this throws an exception since it did not match, we are setting lenient to true to get around this
        given(specialtyRepository.save(argThat(argument ->
                argument.getDescription().equals(MATCH_ME)))).willReturn(savedSpecialty);

        //when
        Speciality returnedSpecialty = service.save(speciality);

        //then
        assertNull(returnedSpecialty);
    }
}