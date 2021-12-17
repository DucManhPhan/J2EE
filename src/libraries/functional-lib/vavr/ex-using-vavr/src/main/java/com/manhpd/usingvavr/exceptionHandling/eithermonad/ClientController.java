package com.manhpd.usingvavr.exceptionHandling.eithermonad;

public class ClientController {

    private MockClientRepository clientRepository = new MockClientRepository();

    private MockEmployeeRepository employeeRepository = new MockEmployeeRepository();

    public String getAssignedEmployeeNameById(int clientId) {
        return this.clientRepository.getById(clientId)
                .map(Client::getEmployeeId)
                .flatMap(this.employeeRepository::getById)
                .map(Employee::getName)
                .fold(error -> "error: " + error,
                      name -> name
                );
    }

}
