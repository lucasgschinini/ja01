package ar.com.telecom.shiva.batch.springbatch.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.util.Assert;

import ar.com.telecom.shiva.batch.springbatch.model.Employee;

public class EmployeeImporterProcessor implements ItemProcessor<Employee, Employee>{

    private static final String NAME_MESSAGE = "el NOMBRE";
    private static final String LAST_NAME_MESSAGE = "los APELLIDOS";

    @Override
    public Employee process(Employee readEmployee) throws Exception {

        checkRequiredFields(readEmployee);

        return readEmployee;
    }

    private void checkRequiredFields(Employee employee) {
        Assert.hasLength(employee.getName(), requiredFieldError(NAME_MESSAGE, employee));
        Assert.hasLength(employee.getLastName(), requiredFieldError(LAST_NAME_MESSAGE, employee));
    }

    private String requiredFieldError(String requiredFieldText, Employee employee) {
        StringBuilder sb = new StringBuilder();

        sb.append(": Es necesario %s en empleado. ");
        sb.append(employee.toString());

        return String.format(sb.toString(), requiredFieldText);
    }
}
