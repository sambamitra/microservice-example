package uk.gov.dwp.mcp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/api/employee")
@Api(value = "/employee", tags = { "Employee" }, description = "Operations related to the Employee")
public class ServiceController {

	private static Logger LOGGER = LoggerFactory.getLogger(ServiceController.class);

	@GetMapping(value = "/{id}", produces = MediaType.TEXT_PLAIN_VALUE)
	@ApiOperation(httpMethod = "GET", value = "/available", notes = "This endpoint fetches the employee based on id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Employee fetched successfully"),
			@ApiResponse(code = 404, message = "No employee found"),
			@ApiResponse(code = 500, message = "Error while processing the request") })
	public ResponseEntity<String> getEmployee(
			@ApiParam(value = "The id of the employee") @PathVariable("id") final String id) {
		LOGGER.info("Getting employee details");
		return new ResponseEntity<>("Hello Docker World!", HttpStatus.OK);
	}
}
