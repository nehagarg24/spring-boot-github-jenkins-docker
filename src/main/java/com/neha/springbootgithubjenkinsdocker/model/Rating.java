package com.neha.springbootgithubjenkinsdocker.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@Document("vendor_ratings")
public class Rating {
	private String ratingId;
	private String ratingName;
	private String vendorId;

}
