package com.lcaohoanq.fucar.controllers;

import com.lcaohoanq.fucar.constants.ResourcePaths;
import com.lcaohoanq.fucar.models.Account;
import com.lcaohoanq.fucar.services.AccountService;
import com.lcaohoanq.fucar.services.IAccountService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import lombok.Setter;

@Setter
public class MyProfileController {

    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label usernameLabel;
    @FXML
    private Label roleLabel;
    @FXML
    private Label passwordLabel;
    @FXML
    private Button editButton;
    @FXML
    private Button deleteButton;

    private Account existingAccount;

    private Account selectedAccount; // Selected account to be displayed

    private final IAccountService accountService;

    public MyProfileController() {
        this.accountService = new AccountService(ResourcePaths.HIBERNATE_CONFIG);
    }

    @FXML
    public void initialize() {
        // Load a specific account (e.g., based on login or selection from another screen)
        loadAccountProfile(LoginController.account); // For demo, loading the first account
    }

    public void loadAccountProfile(Account account) {
        selectedAccount = account;

        // Set labels with account data
        firstNameLabel.setText(account.getAccountName());
        lastNameLabel.setText(account.getAccountName());
//        usernameLabel.setText(account.getUsername());
//        roleLabel.setText(account.getRole().name()); // Role is an enum, so get its name
//        passwordLabel.setText(account.getPassword());
    }

    @FXML
    private void handleEditButtonAction() {
//        // Logic for editing account profile
//        System.out.println("Editing account: " + selectedAccount.getUsername());
//        // Implement edit logic here (e.g., open edit form or enable fields)
    }

    @FXML
    private void handleDeleteButtonAction() {
//        // Logic for deleting account profile
//        System.out.println("Deleting account: " + selectedAccount.getUsername());
//        // Implement delete logic here (e.g., confirmation dialog and delete from database)
    }
}
