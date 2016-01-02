package com.rest

class Cube {
	
	int id
	
	String name
	
	static hasMany=[contents:Content,users:User]
	
	static belongsTo=User
	
	static constraints = {
		name nullable:false
    	}
}
