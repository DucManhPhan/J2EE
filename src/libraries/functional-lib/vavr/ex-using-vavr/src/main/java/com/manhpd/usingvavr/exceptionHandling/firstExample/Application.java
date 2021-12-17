package com.manhpd.usingvavr.exceptionHandling.firstExample;

public class Application {

    public static void main(String[] args) {
        ClientController controller = new ClientController();
        String nameOrError = controller.getAssignedEmployeeNameById(1);

        System.out.println(nameOrError);
    }

}
