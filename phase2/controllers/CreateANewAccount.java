package controllers;

import use_cases.UserFactory;

public class CreateANewAccount {

    private final UserFactory userFactory;

    public CreateANewAccount(){
        this.userFactory = new UserFactory();
    }
    public void toCreateANewAccount(String email, String userName, String password, String type){
        userFactory.createANewUser(email, userName, password, type);
    }
}
