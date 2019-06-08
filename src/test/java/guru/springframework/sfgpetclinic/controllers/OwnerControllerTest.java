package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.fauxspring.BindingResult;
import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    private static final String REDIRECT_OWNERS_5 = "redirect:/owners/5";
    private static final String OWNERS_CREATE_OR_UPDATE_OWNER_FORM = "owners/createOrUpdateOwnerForm";
    @Mock
    private BindingResult bindingResult;

    @Mock
    private OwnerService ownerService;

    @InjectMocks
    private OwnerController controller;

    @Captor
    ArgumentCaptor<String> argumentCaptor;

    @Test
    @DisplayName("Test correct view name is returned from controller")
    void processCreationFormWithoutErrors() {

        //given
        Owner owner = new Owner(5L, "Lucy", "Lou");
        given(bindingResult.hasErrors()).willReturn(false);
        given(ownerService.save(any())).willReturn(owner);


        //when
        String viewName = controller.processCreationForm(owner, bindingResult);

        //then
        assertThat(viewName).isEqualToIgnoringCase(REDIRECT_OWNERS_5);


    }

    @Test
    void processCreationFormWithErrors() {
        //given
        Owner owner = new Owner(1L, "Lucy", "Lou");
        given(bindingResult.hasErrors()).willReturn(true);

        //when
        String viewName = controller.processCreationForm(owner, bindingResult);

        //then
        assertThat(viewName).isEqualToIgnoringCase(OWNERS_CREATE_OR_UPDATE_OWNER_FORM);


    }
    @Test
    void processFindForm() {
        //given
        Owner owner = new Owner(1L, "Lucy", "Lou");
        List<Owner> ownerList = new ArrayList<>();
        final ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        given(ownerService.findAllByLastNameLike(captor.capture())).willReturn(ownerList);

        //when
        String viewName = controller.processFindForm(owner, bindingResult, null);
        //then
     //   assertThat(captor.getValue()).isEqualToIgnoringCase("Lou");
        assertThat("%Lou%").isEqualToIgnoringCase(captor.getValue());
    }

    @Test
    void processFindFormAnotherTypeOfCaptor() {
        //this uses the argument captor annotated above
        //given
        Owner owner = new Owner(1L, "Lucy", "Lou");
        List<Owner> ownerList = new ArrayList<>();

        given(ownerService.findAllByLastNameLike(argumentCaptor.capture())).willReturn(ownerList);

        //when
        String viewName = controller.processFindForm(owner, bindingResult, null);
        //then
        //   assertThat(captor.getValue()).isEqualToIgnoringCase("Lou");
        assertThat("%Lou%").isEqualToIgnoringCase(argumentCaptor.getValue());
    }
}