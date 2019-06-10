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

import com.saminda.controller.DepartmentController;
import com.saminda.model.Department;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/service/department")
public class DepartmentEndpoints {
	
	@Autowired
	DepartmentController departmentController;
	
	Logger logger = Logger.getLogger(DepartmentEndpoints.class);
	
	
	//Create New Department ( POST) 
	
	@SuppressWarnings("unchecked")
	@ResponseStatus(value = HttpStatus.OK)
	@ApiOperation(value = "Add New Department", response = Iterable.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Department Successfully Inserted"),
	@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@RequestMapping(value = "/add_department", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public synchronized @ResponseBody ResponseEntity<JSONObject> insertEmployee(@RequestParam("name") String name,
			@RequestParam("dep_id") int dep_id) {

		JSONObject responseObj = new JSONObject();
		logger.info("request_type:" + "POST" + " " + " " + "request_mappingn:" + "'/insert_brand'");
		try{
		
	    	responseObj.put("value", "Department Successfully Added..!");
			responseObj.put("code", 200);
			responseObj.put("message", "Success");
			departmentController.insertDepartment(name, dep_id);
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
	
	//Update Department ( PUT ) 
	
	@SuppressWarnings("unchecked")
	@ResponseStatus(value = HttpStatus.OK)
	@ApiOperation(value = "Update Department", response = Iterable.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Department Successfully Updated"),
	@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
    @RequestMapping(value = "/update_department", method= RequestMethod.PUT, produces =  MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONObject> updateDepartment(@RequestParam("id") int id, @RequestParam("name") String name,
			@RequestParam("dep_id") int dep_id) {
		
    	JSONObject responseObj = new JSONObject();
    	if (!departmentController.getDepartment(id).isPresent()) {
    		
    		System.out.println( id + "is not existed");
            ResponseEntity.badRequest().build();
            responseObj.put("value", "Department ID Not Fount..!");
			responseObj.put("code", 404);
			responseObj.put("message", "Not Found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseObj);
        }
    	else{

    	departmentController.updateDepartment(id,name,dep_id);
    	responseObj.put("value", "Department Successfully Update..!");
		responseObj.put("code", 200);
		responseObj.put("message", "Success");
    	 return ResponseEntity.status(HttpStatus.OK).body(responseObj);
    	}
       
    }
	
	//Get All Departments ( GET) 
	
	@SuppressWarnings("unchecked")
	@ResponseStatus(value = HttpStatus.OK)
	@ApiOperation(value = "Get All Departments", response = Iterable.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Get All Departments"),
	@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@RequestMapping(value = "/department_list", method= RequestMethod.GET, produces =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JSONObject> getAllDepartments(){
	   	JSONObject responseObj = new JSONObject();
	 try{
		 	List<Department> departmentlist = departmentController.getAllDepartments();
	    	responseObj.put("value", departmentlist);
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
	
	// Get Department details by Department ID ( GET ) 
	
	@SuppressWarnings("unchecked")
	@ResponseStatus(value = HttpStatus.OK)
	@ApiOperation(value = "Get Department by ID",response = Iterable.class)
	    @ApiResponses(value = {
	            @ApiResponse(code = 200, message = "Successfully retrieved Department by ID"),
	            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	    }
	    )
	  @RequestMapping(value = "/getDepartmentID", method= RequestMethod.GET, produces =  MediaType.APPLICATION_JSON_VALUE)
	  public ResponseEntity<JSONObject> findById(@RequestParam("id") int id) {
	       
	        JSONObject responseObj = new JSONObject();
	        if (!departmentController.getDepartment(id).isPresent()) {
	    		
	    		System.out.println( id + "is not existed");
	            ResponseEntity.badRequest().build();
	            responseObj.put("value", "Department ID Not Fount..!");
				responseObj.put("code", 404);
				responseObj.put("message", "Not Found");
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseObj);
	        }
	    	else{

	    	Optional<Department> department =departmentController.getDepartment(id);
	    	responseObj.put("value",department);
			responseObj.put("code", 200);
			responseObj.put("message", "Success");
	    	return ResponseEntity.status(HttpStatus.OK).body(responseObj);
	    	}
	    }
	
	
	
	
	// Delete Department ( DELETE) 
	
	@SuppressWarnings("unchecked")
	@ResponseStatus(value = HttpStatus.OK)
	 @ApiOperation(value = "Delete Department",response = Iterable.class)
	    @ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Successfully Delete Department"),
	    @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	    @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	    }
	    )
	 @RequestMapping(value = "/delete_department", method= RequestMethod.DELETE, produces =  MediaType.APPLICATION_JSON_VALUE)
	 public ResponseEntity<JSONObject> deleteDepartment(@RequestParam("id") int id){
		 
		 JSONObject responseObj = new JSONObject();
	    	if (!departmentController.getDepartment(id).isPresent()) {
	    		
	    		System.out.println( id + "is not existed");
	            ResponseEntity.badRequest().build();
	            responseObj.put("value", "Department ID Not Fount..!");
				responseObj.put("code", 404);
				responseObj.put("message", "Not Found");
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseObj);
	        }
	    	else{

	    	departmentController.deleteDepartment(id);
	    	responseObj.put("value", "Department Successfully Deleted..!");
			responseObj.put("code", 200);
			responseObj.put("message", "Success");
	    	return ResponseEntity.status(HttpStatus.OK).body(responseObj);
	    	}
	 }
	
	
	

}
