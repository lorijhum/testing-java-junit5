package guru.springframework.sfgpetclinic.controllers;

class IndexController {

    String index(){

        return "index";
    }

    String oupsHandler(){

        throw new ValueNotFoundException();
    }
}
