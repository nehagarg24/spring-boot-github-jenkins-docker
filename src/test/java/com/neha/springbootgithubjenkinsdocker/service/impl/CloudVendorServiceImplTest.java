package com.neha.springbootgithubjenkinsdocker.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.neha.springbootgithubjenkinsdocker.model.CloudVendor;
import com.neha.springbootgithubjenkinsdocker.repository.CloudVendorRepository;
import com.neha.springbootgithubjenkinsdocker.service.CloudVendorService;

class CloudVendorServiceImplTest {

	@Mock
	private CloudVendorRepository cloudVendorRepository;
	private CloudVendorService cloudVendorService;
	AutoCloseable autoCloseable;
	@Mock
	CloudVendor cloudVendor;

	@BeforeEach
	void setUp() {
		autoCloseable = MockitoAnnotations.openMocks(this);
		cloudVendorService = new CloudVendorServiceImpl(cloudVendorRepository);
		cloudVendor = new CloudVendor("id1", "name1", "address1", "phone1");
	}

	@AfterEach
	void tearDown() throws Exception{
		autoCloseable.close();
	}
	@Test
	void testGetCloudVendor() {
		mock(CloudVendor.class);
		mock(CloudVendorRepository.class);

		when(cloudVendorRepository.findById("id1")).thenReturn(Optional.ofNullable(cloudVendor));
		assertThat(cloudVendorService.getCloudVendor(cloudVendor.getVendorId())).isEqualTo(cloudVendor);
	}

	@Test
	void testCreateCloudVendor() {
		mock(CloudVendor.class);
		mock(CloudVendorRepository.class);

		when(cloudVendorRepository.save(cloudVendor)).thenReturn(cloudVendor);
		assertThat(cloudVendorService.createCloudVendor(cloudVendor)).isEqualTo("Success");
	}

	@Test
	void testUpdateCloudVendor() {
		mock(CloudVendor.class);
		mock(CloudVendorRepository.class);

		when(cloudVendorRepository.save(cloudVendor)).thenReturn(cloudVendor);
		assertThat(cloudVendorService.updateCloudVendor(cloudVendor)).isEqualTo("Updated with success");
	}

	@Test
	void deleteCloudVendor() {
		mock(CloudVendor.class);
		mock(CloudVendorRepository.class, Mockito.CALLS_REAL_METHODS);

		doAnswer(Answers.CALLS_REAL_METHODS).when(cloudVendorRepository).deleteById(any());
		assertThat(cloudVendorService.deleteCloudVendor(cloudVendor.getVendorId())).isEqualTo("Deleted with success");
	}
	@Test
	void testGetAllCloudVendors() {
		mock(CloudVendor.class);
		mock(CloudVendorRepository.class);

		when(cloudVendorRepository.findAll()).thenReturn(new ArrayList<>(Collections.singleton(cloudVendor)));
		assertThat(cloudVendorService.getAllCloudVendors().get(0).getVendorPhoneNumber()).isEqualTo(cloudVendor.getVendorPhoneNumber());
	}

	@Test
	void testGetByVendorName() {
		mock(CloudVendor.class);
		mock(CloudVendorRepository.class);

		when(cloudVendorRepository.findByVendorName(cloudVendor.getVendorName()))
				.thenReturn(new ArrayList<>(Collections.singleton(cloudVendor)));

		assertThat(cloudVendorService.getByVendorName("name1").get(0).getVendorName()).isEqualTo(cloudVendor.getVendorName());
	}
}