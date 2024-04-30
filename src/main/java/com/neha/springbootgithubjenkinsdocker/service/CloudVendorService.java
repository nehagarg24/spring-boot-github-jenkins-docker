package com.neha.springbootgithubjenkinsdocker.service;

import java.util.List;

import com.neha.springbootgithubjenkinsdocker.model.CloudVendor;

public interface CloudVendorService {

	public CloudVendor getCloudVendor(String vendorId);
	public String createCloudVendor(CloudVendor cloudVendor);
	public String updateCloudVendor(CloudVendor cloudVendor);

	public String deleteCloudVendor(String vendorId);

	public List<CloudVendor> getAllCloudVendors();
	public List<CloudVendor> getByVendorName(String vendorName);

}
