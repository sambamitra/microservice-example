package uk.gov.dwp.mcp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/api/book")
@Api(value = "/book", tags = { "Book" }, description = "Operations related to the Book")
public class ServiceController {

	private static Logger LOGGER = LoggerFactory.getLogger(ServiceController.class);

	@GetMapping(value = "/available", produces = MediaType.TEXT_PLAIN_VALUE)
	@ApiOperation(httpMethod = "GET", value = "/available", notes = "This endpoint fetches the available books")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Available books fetched successfully"),
			@ApiResponse(code = 404, message = "No available book not found"),
			@ApiResponse(code = 500, message = "Error while processing the request") })
	public ResponseEntity<String> available() {
		LOGGER.info("Fetching available books");
		return new ResponseEntity<String>("Spring in Action", HttpStatus.OK);
	}

	@GetMapping(value = "/checked-out", produces = MediaType.TEXT_PLAIN_VALUE)
	@ApiOperation(httpMethod = "GET", value = "/available", notes = "This endpoint fetches the checked out books")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Checked out books fetched successfully"),
			@ApiResponse(code = 404, message = "No checked out book not found"),
			@ApiResponse(code = 500, message = "Error while processing the request") })
	public ResponseEntity<String> checkedOut() {
		LOGGER.info("Fetching checked out books");
		return new ResponseEntity<String>("Spring Boot in Action", HttpStatus.OK);
	}

}