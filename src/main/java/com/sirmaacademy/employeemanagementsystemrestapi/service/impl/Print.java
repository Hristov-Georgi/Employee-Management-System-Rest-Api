package com.sirmaacademy.employeemanagementsystemrestapi.service.impl;

import com.sirmaacademy.employeemanagementsystemrestapi.model.entity.Account;

public final class Print {

    private Print() {
    }

    public static void printAccountDetails(Account account) {
        System.out.printf(
                        "Username: %s%n" +
                        "Password: %s%n" +
                        "Employee: %s, %s, %s%n" +
                        "Department: %s%n" +
                        "%n* Change default password with more secure one!%n",
                account.getUsername(),
                account.getPassword(),
                account.getEmployee().getFirstName(),
                account.getEmployee().getMiddleName(),
                account.getEmployee().getLastName(),
                account.getEmployee().getDepartment());
    }
}
