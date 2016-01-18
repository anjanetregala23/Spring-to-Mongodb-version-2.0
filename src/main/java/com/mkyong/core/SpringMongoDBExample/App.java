package com.mkyong.core.SpringMongoDBExample;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.mkyong.config.SpringMongoConfig;
import com.mkyong.model.Employee;
//import org.springframework.context.support.GenericXmlApplicationContext;

public class App {

    public static void main(String[] args) {

	// For XML
	//ApplicationContext ctx = new GenericXmlApplicationContext("SpringConfig.xml");

	// For Annotation
	ApplicationContext ctx = 
             new AnnotationConfigApplicationContext(SpringMongoConfig.class);
	MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");

	Employee employee = new Employee();

	// save
	mongoOperation.save(employee);

	// now Employee object got the created id.
	System.out.println("1. employee_name : " + employee);

	// query to search Employee
	Query searchEmployeeQuery = new Query(Criteria.where("name").is("Leo"));

	// find the saved Employee again.
	Employee savedEmployee = mongoOperation.findOne(searchEmployeeQuery, Employee.class);
	System.out.println("2. find - savedEmployee : " + savedEmployee);

	// update password
	mongoOperation.updateFirst(searchEmployeeQuery, 
                         Update.update("age", "new age"),Employee.class);

	// find the updated Employee object
	Employee updatedEmployee = mongoOperation.findOne(searchEmployeeQuery, Employee.class);

	System.out.println("3. updatedEmployee : " + updatedEmployee);

	// delete
	mongoOperation.remove(searchEmployeeQuery, Employee.class);

	// List, it should be empty now.
	List<Employee> listEmployee = mongoOperation.findAll(Employee.class);
	System.out.println("4. Number of employee = " + listEmployee.size());

    }

}