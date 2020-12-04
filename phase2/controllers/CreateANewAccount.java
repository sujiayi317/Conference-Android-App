package controllers;

import use_cases.UserFactory;

public class CreateANewAccount {

    private final UserFactory userFactory;
    private static OutputManager output;
    private static InputManager input;

    public CreateANewAccount(){
        this.userFactory = new UserFactory();
        input = new InputManager();
        output = new OutputManager();
    }
    public void toCreateANewAccount(){

    }
}
