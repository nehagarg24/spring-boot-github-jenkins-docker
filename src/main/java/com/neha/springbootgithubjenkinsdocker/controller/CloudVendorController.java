package com.neha.springbootgithubjenkinsdocker.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.neha.springbootgithubjenkinsdocker.model.CloudVendor;
import com.neha.springbootgithubjenkinsdocker.response.ResponseHandler;
import com.neha.springbootgithubjenkinsdocker.service.CloudVendorService;

@RestController
@RequestMapping("/cloudvendor")
public class CloudVendorController {

	//In order to use logger in the class, we have to define log variable like this:
	private static final Logger logInfo = LoggerFactory.getLogger(CloudVendorController.class);
	private CloudVendorService cloudVendorService;

	public CloudVendorController(CloudVendorService cloudVendorService) {
		this.cloudVendorService = cloudVendorService;
	}

//	@GetMapping("/{cloudVendorId}")
//	public CloudVendor getCloudVendor(@PathVariable("cloudVendorId") String cloudVendorId) {
//		CloudVendor cloudVendor1 = new CloudVendor(cloudVendorId, "name1 + "+cloudVendorId, "address1 + "+cloudVendorId,
//				"phone1 + "+cloudVendorId);
////		Posts posts = restTemplate.getForObject("http://localhost:8081/post/1", Posts.class);
////		cloudVendor1.setPosts(posts);
//		Notifications notifications = restTemplate.getForObject("http://localhost:8080/notification/1", Notifications.class);
//		cloudVendor1.setNotifications(notifications);
//		return cloudVendor1;
//	}

	@GetMapping("{vendorId}")
	public ResponseEntity<Object> getCloudVendorDetails(@PathVariable("vendorId") String vendorId) {

		logInfo.info("CloudVendor info is enabled");
		logInfo.debug("CloudVendor debug is also enabled"); // In order to enable debug logs, we have to manually do it in application.properties OR application.yaml file.
		return ResponseHandler.responseBuilder("Requested vendor details are: ", HttpStatus.OK, cloudVendorService.getCloudVendor(vendorId));
	}

	@GetMapping
	public List<CloudVendor> getAllCloudVendorDetails() {
		return cloudVendorService.getAllCloudVendors();
	}

	@PostMapping
	public String createCloudVendorDetails(@RequestBody CloudVendor cloudVendor) {
		cloudVendorService.createCloudVendor(cloudVendor);
		return "Cloud vendor created sucessfully";
	}

	@PutMapping
	public String updateCloudVendorDetails(@RequestBody CloudVendor cloudVendor) {
		cloudVendorService.updateCloudVendor(cloudVendor);
		return "Cloud vendor updated sucessfully";
	}

	@DeleteMapping("{vendorId}")
	public String deleteCloudVendorDetails(@PathVariable("vendorId") String vendorId) {
		cloudVendorService.deleteCloudVendor(vendorId);
		return "Cloud vendor deleted sucessfully";
	}
}
