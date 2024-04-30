package com.neha.springbootgithubjenkinsdocker.repository;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.neha.springbootgithubjenkinsdocker.model.CloudVendor;

@DataJpaTest
class CloudVendorRepositoryTest {

	@Autowired
	CloudVendorRepository cloudVendorRepository;
	CloudVendor cloudVendor;
	@BeforeEach
	void setUp() {
		cloudVendor = new CloudVendor("1", "name1", "address1", "phone1");
		cloudVendorRepository.save(cloudVendor);
	}

	@AfterEach
	void tearDown() {
		cloudVendor=null;
		cloudVendorRepository.deleteAll();
	}

	@Test
	void testFindByVendorName_found() {
		List<CloudVendor> cloudVendorList = cloudVendorRepository.findByVendorName("name1");

		assertThat(cloudVendorList.get(0).getVendorId()).isEqualTo(cloudVendor.getVendorId());
		assertThat(cloudVendorList.get(0).getVendorAddress()).isEqualTo(cloudVendor.getVendorAddress());
	}

	@Test
	void testFindByVendorName_NotFound() {
		List<CloudVendor> cloudVendorList = cloudVendorRepository.findByVendorName("xyz");
		assertThat(cloudVendorList.isEmpty()).isTrue();
	}
}