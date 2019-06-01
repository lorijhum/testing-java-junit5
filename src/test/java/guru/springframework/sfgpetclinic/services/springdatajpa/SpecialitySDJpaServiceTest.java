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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SpecialitySDJpaServiceTest {

    @Mock
    SpecialtyRepository specialtyRepository;

    @InjectMocks
    SpecialitySDJpaService service;

    @Test
    void findById() {
        //here we create an instance of Speciality
        Speciality speciality = new Speciality();
        // then we say when calling findById(1L), we return the speciality we just created above
        when(specialtyRepository.findById(1L)).thenReturn(Optional.of(speciality));

        //here we find the speciality by id
        Speciality foundSpeciality = service.findById(1L);


        assertThat(foundSpeciality).isNotNull();

        //here we verify that the method was called once (that is the default of verify, once)
        verify(specialtyRepository).findById(1L);

        //here we verify that the method was called to find any id of type long
        verify(specialtyRepository).findById(anyLong());
    }

    @Test
    void deleteByObject() {
        Speciality speciality = new Speciality();
        service.delete(speciality);

        //using an argument matcher in the verify to check that we deleted an object of the Speciality Class
        verify(specialtyRepository).delete(any(Speciality.class));
    }
    @Test
    void deleteById() {
        service.deleteById(1L);
        service.deleteById(1L);
        // the default for the verify below is 1, so without ,times, it verifies the method was called once
        verify(specialtyRepository,times(2)).deleteById(1L);
    }

    @Test
    void deleteByIdAtLeast() {
        service.deleteById(1L);
        service.deleteById(1L);
        //checks that the method was called at least one time
        verify(specialtyRepository, atLeastOnce()).deleteById(1L);
    }

    @Test
    void deleteByIdAtMost() {
        service.deleteById(1L);
        service.deleteById(1L);
        service.deleteById(1L);
        //checks that the method was called at most 5 times
        verify(specialtyRepository, atMost(5)).deleteById(1L);
    }

    @Test
    void deleteByIdNever() {
        service.deleteById(1L);
        service.deleteById(1L);
        service.deleteById(1L);
        //checks that the method was called at most 5 times
        verify(specialtyRepository, atMost(5)).deleteById(1L);
        //checks that it was never called for id 2
        verify(specialtyRepository, never()).deleteById(2L);
    }

    @Test
    void delete() {
        service.delete(new Speciality());
    }
}