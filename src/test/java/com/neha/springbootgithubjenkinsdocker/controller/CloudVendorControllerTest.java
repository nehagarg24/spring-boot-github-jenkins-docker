package com.neha.springbootgithubjenkinsdocker.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.neha.springbootgithubjenkinsdocker.model.CloudVendor;
import com.neha.springbootgithubjenkinsdocker.service.CloudVendorService;

@WebMvcTest(CloudVendorController.class)
class CloudVendorControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private CloudVendorService cloudVendorService;
	CloudVendor cloudVendor1;
	CloudVendor cloudVendor2;
	List<CloudVendor> cloudVendorList = new ArrayList<>();

	@BeforeEach
	void setUp() {
		cloudVendor1 = new CloudVendor("id1", "name1", "address1", "phone1");
		cloudVendor2 = new CloudVendor("id2", "name2", "address2", "phone2");
		cloudVendorList.add(cloudVendor1);
		cloudVendorList.add(cloudVendor2);
	}

	@AfterEach
	void tearDown() {
	}

	@Test
	void testGetCloudVendorDetails() throws Exception {
		when(cloudVendorService.getCloudVendor("id1"))
				.thenReturn(cloudVendor1);
		this.mockMvc.perform(get("/cloudVendor/"+"id1"))
				.andDo(print()).andExpect(status().isOk());
	}

	@Test
	void testGetAllCloudVendorDetails() throws Exception {
		when(cloudVendorService.getAllCloudVendors())
				.thenReturn(cloudVendorList);
		this.mockMvc.perform(get("/cloudVendor"))
				.andDo(print()).andExpect(status().isOk());
	}

	@Test
	void testCreateCloudVendorDetails() throws Exception {

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson=ow.writeValueAsString(cloudVendor1);

		when(cloudVendorService.createCloudVendor(cloudVendor1))
				.thenReturn("Cloud Vendor created Successfully");
		this.mockMvc.perform(post("/cloudVendor")
				.contentType(MediaType.APPLICATION_JSON)
						.content(requestJson))
				.andDo(print()).andExpect(status().isOk());
	}

	@Test
	void updateCloudVendorDetails() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson=ow.writeValueAsString(cloudVendor1);

		when(cloudVendorService.updateCloudVendor(cloudVendor1))
				.thenReturn("Cloud Vendor Updated Successfully");
		this.mockMvc.perform(put("/cloudVendor")
						.contentType(MediaType.APPLICATION_JSON)
						.content(requestJson))
				.andDo(print()).andExpect(status().isOk());
	}

	@Test
	void deleteCloudVendorDetails() throws Exception {
		when(cloudVendorService.deleteCloudVendor("1"))
				.thenReturn("Cloud Vendor Deleted Successfully");
		this.mockMvc.perform(delete("/cloudVendor/" + "1"))
				.andDo(print()).andExpect(status().isOk());
	}


}