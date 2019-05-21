/**
 * 
 */
package ar.com.telecom.shiva.batch.springbatch.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

import ar.com.telecom.shiva.batch.springbatch.model.Employee;

/**
 * @author u564030 Pablo M. Ibarrola
 *
 */
public class EmployeeImporterWriter implements ItemWriter<Employee> {
 
    @Override
    public void write(List<? extends Employee> items) throws Exception {
        for (Employee employee : items) {
            System.out.println(employee.toString());
        }
    }
}