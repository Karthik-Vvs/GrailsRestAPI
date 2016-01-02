package com.rest

class User {
	int id
	String name
	String city
	List cubes
	List contents
	
	static hasMany=[cubes:Cube,contents:Content]
	
	static constraints = {
		name nullable:false
		city nullable:false
    	}
}
