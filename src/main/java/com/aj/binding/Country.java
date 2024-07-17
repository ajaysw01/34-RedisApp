package com.aj.binding;

import java.io.Serializable;

import lombok.Data;

@Data
public class Country implements Serializable{

	static final long serialVersionUID = 1L;
	private Integer id;
    private String name;
    private String countryCode;
   
    
}
