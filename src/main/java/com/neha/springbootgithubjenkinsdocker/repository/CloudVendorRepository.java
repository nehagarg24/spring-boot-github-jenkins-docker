package com.neha.springbootgithubjenkinsdocker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neha.springbootgithubjenkinsdocker.model.CloudVendor;

public interface CloudVendorRepository extends JpaRepository<CloudVendor,String> {
	List<CloudVendor> findByVendorName(String vendorName);
}
