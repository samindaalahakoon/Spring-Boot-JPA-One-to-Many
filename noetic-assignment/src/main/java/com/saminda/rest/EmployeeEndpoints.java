package com.saminda.rest;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.saminda.controller.EmployeeController;
import com.saminda.model.Department;
import com.saminda.model.Employee;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/service/employee")
public class EmployeeEndpoints {
	
	@Autowired
	EmployeeController employeeController;
	
	Logger logger = Logger.getLogger(EmployeeEndpoints.class);
	
	
	// Create new Employee ( POST ) 
	
	@SuppressWarnings("unchecked")
	@ResponseStatus(value = HttpStatus.OK)
	@ApiOperation(value = "Add New Employee", response = Iterable.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Employee Successfully Inserted"),
	@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@RequestMapping(value = "/add_employee", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public synchronized @ResponseBody ResponseEntity<JSONObject> insertEmployee(@RequestParam("firstname") String firstname,@RequestParam("lastname") String lastname,@RequestParam("employeeID") int employeeID, @RequestParam("dep_id")Department dep_id) {

		System.out.println("firstname : "+ firstname +" lastname : " + lastname +" employeeID "+employeeID + " dep_id : " + dep_id);
		JSONObject responseObj = new JSONObject();
		logger.info("request_type:" + "POST" + " " + " " + "request_mappingn:" + "'/insert_brand'");
		try{
		if (dep_id == null) {
	            ResponseEntity.badRequest().build();
	            responseObj.put("value", "Department ID Not Fount..!");
				responseObj.put("code", 404);
				responseObj.put("message", "Not Found");
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseObj);
	        }
		else{
	    	responseObj.put("value", "Employee Successfully Added..!");
			responseObj.put("code", 200);
			responseObj.put("message", "Success");
			employeeController.insertEmployee(firstname, lastname, employeeID, dep_id);
	    	return ResponseEntity.status(HttpStatus.OK).body(responseObj);
	    	}
		}
		catch (Exception e) {
			System.out.println("inside e " + e.getMessage());
			responseObj.put("value", e.getMessage());
			responseObj.put("code", 500);
			responseObj.put("message", "Internal Server Error");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseObj);
		}

	}
	
	// Update Employee ( PUT ) 
	
	@SuppressWarnings("unchecked")
	@ResponseStatus(value = HttpStatus.OK)
	@ApiOperation(value = "Update Employee", response = Iterable.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Employee Successfully Updated"),
	@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
    @RequestMapping(value = "/update_employee", method= RequestMethod.PUT, produces =  MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONObject> updateEmployee(@RequestParam("id") int id, @RequestParam("firstname") String firstname,
			@RequestParam("lastname") String lastname,@RequestParam("emp_id") int emp_id,@RequestParam("dep_id") Department dep_id) {
    	JSONObject responseObj = new JSONObject();
    	if (!employeeController.getEmployee(id).isPresent()) {
    		
    		System.out.println( id + "is not existed");
            ResponseEntity.badRequest().build();
            responseObj.put("value", "Employee ID Not Fount..!");
			responseObj.put("code", 404);
			responseObj.put("message", "Not Found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseObj);
        }
    	else{

    	employeeController.updateEmployee(id, firstname, lastname, emp_id, dep_id);
    	responseObj.put("value", "Department Successfully Update..!");
		responseObj.put("code", 200);
		responseObj.put("message", "Success");
    	 return ResponseEntity.status(HttpStatus.OK).body(responseObj);
    	}
       
    }
	
	// Get All Employees ( GET ) 
	
	@SuppressWarnings("unchecked")
	@ResponseStatus(value = HttpStatus.OK)
	@ApiOperation(value = "Get All Employees", response = Iterable.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Get All Employees"),
	@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@RequestMapping(value = "/emp_list", method= RequestMethod.GET, produces =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JSONObject> getAllEmployees(){
	   	JSONObject responseObj = new JSONObject();
	 try{
		 	List<Employee> employeeList = employeeController.getAllEmployees();
	    	responseObj.put("value", employeeList);
			responseObj.put("code", 200);
			responseObj.put("message", "Success");
	    	return ResponseEntity.status(HttpStatus.OK).body(responseObj);

	 }
	 catch (Exception e) {
		 	System.out.println("inside e " + e.getMessage());
			responseObj.put("value", e.getMessage());
			responseObj.put("code", 500);
			responseObj.put("message", "Internal Server Error");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseObj);
	}
	 
    }
	
	
	// Get Employee Details by Employee ID ( GET ) 
	
	
		@SuppressWarnings("unchecked")
		@ResponseStatus(value = HttpStatus.OK)
		@ApiOperation(value = "Get Employee by ID",response = Iterable.class)
		    @ApiResponses(value = {
		            @ApiResponse(code = 200, message = "Successfully retrieved Employee by ID"),
		            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
		            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
		            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
		    }
		    )
		  @RequestMapping(value = "/getEmployeeID", method= RequestMethod.GET, produces =  MediaType.APPLICATION_JSON_VALUE)
		  public ResponseEntity<JSONObject> findById(@RequestParam("id") int id) {
		       
		        JSONObject responseObj = new JSONObject();
		        if (!employeeController.getEmployee(id).isPresent()) {
		    		
		    		System.out.println( id + "is not existed");
		            ResponseEntity.badRequest().build();
		            responseObj.put("value", "Employee ID Not Fount..!");
					responseObj.put("code", 404);
					responseObj.put("message", "Not Found");
		            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseObj);
		        }
		    	else{

		    	Optional<Employee> employee = employeeController.getEmployee(id);
		    	responseObj.put("value",employee);
				responseObj.put("code", 200);
				responseObj.put("message", "Success");
		    	return ResponseEntity.status(HttpStatus.OK).body(responseObj);
		    	}
		    }

	
	
	// Delete Employee ( DELETE ) 
	
	@SuppressWarnings("unchecked")
	@ResponseStatus(value = HttpStatus.OK)
	 @ApiOperation(value = "Delete Employee",response = Iterable.class)
	    @ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Successfully Delete Employee"),
	    @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	    @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	    }
	    )
	 @RequestMapping(value = "/delete_employee", method= RequestMethod.DELETE, produces =  MediaType.APPLICATION_JSON_VALUE)
	 public ResponseEntity<JSONObject> deleteDepartment(@RequestParam("id") int id){
		 
		 JSONObject responseObj = new JSONObject();
	    	if (!employeeController.getEmployee(id).isPresent()) {
	    		
	    		System.out.println( id + "is not existed");
	            ResponseEntity.badRequest().build();
	            responseObj.put("value", "Employee ID Not Fount..!");
				responseObj.put("code", 404);
				responseObj.put("message", "Not Found");
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseObj);
	        }
	    	else{

	    	employeeController.deleteEmployee(id);
	    	responseObj.put("value", "Employee Successfully Deleted..!");
			responseObj.put("code", 200);
			responseObj.put("message", "Success");
	    	return ResponseEntity.status(HttpStatus.OK).body(responseObj);
	    	}
	 }
	
	
	

}
