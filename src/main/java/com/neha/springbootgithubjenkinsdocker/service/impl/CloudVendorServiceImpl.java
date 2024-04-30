package com.neha.springbootgithubjenkinsdocker.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.neha.springbootgithubjenkinsdocker.controller.CloudVendorController;
import com.neha.springbootgithubjenkinsdocker.exception.CloudVendorNotFoundException;
import com.neha.springbootgithubjenkinsdocker.model.CloudVendor;
import com.neha.springbootgithubjenkinsdocker.repository.CloudVendorRepository;
import com.neha.springbootgithubjenkinsdocker.service.CloudVendorService;

@Service
public class CloudVendorServiceImpl implements CloudVendorService {

	@Autowired
	CloudVendorRepository cloudVendorRepository;
	@Autowired
	private RestTemplate restTemplate;
	private static final Logger logInfo = LoggerFactory.getLogger(CloudVendorServiceImpl.class);

	public CloudVendorServiceImpl(CloudVendorRepository cloudVendorRepository) {
		this.cloudVendorRepository = cloudVendorRepository;
	}

	@Override
	public CloudVendor getCloudVendor(String vendorId) {
//		if(cloudVendorRepository.findById(vendorId).isEmpty())
//			throw new CloudVendorNotFoundException("Requested Cloud vendor id not found");
		CloudVendor cloudVendor = cloudVendorRepository.findById(vendorId).orElseThrow(() -> new CloudVendorNotFoundException("Requested Cloud vendor id not found " + vendorId));

		ArrayList forObject = restTemplate.getForObject("http://RATING-MS/ratings/cloudvendor/"+cloudVendor.getVendorId(), ArrayList.class);
		logInfo.info("{}", forObject);

		cloudVendor.setRating(forObject);

		return cloudVendor;
	}

	@Override
	public String createCloudVendor(CloudVendor cloudVendor) {
		cloudVendorRepository.save(cloudVendor);
		return "Success";
	}

	@Override
	public String updateCloudVendor(CloudVendor cloudVendor) {
		cloudVendorRepository.save(cloudVendor);
		return "Updated with success";
	}

	@Override
	public String deleteCloudVendor(String vendorId) {
		cloudVendorRepository.deleteById(vendorId);
		return "Deleted with success";
	}

	@Override
	public List<CloudVendor> getAllCloudVendors() {
		return cloudVendorRepository.findAll();
	}

	public List<CloudVendor> getByVendorName(String vendorName) {
		return cloudVendorRepository.findByVendorName(vendorName);
	}
}
