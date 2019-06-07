package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.fauxspring.BindingResult;
import guru.springframework.sfgpetclinic.model.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {



    @Mock
    Owner owner;

    @Mock
    BindingResult bindingResult;

    @InjectMocks
    OwnerController controller;

    @Test
    @DisplayName("Test correct view name is returned from controller")
    void processCreationFormWithoutErrors() {



    }
}