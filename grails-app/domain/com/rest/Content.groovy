package com.rest

class Content {
	
	int id
	
	String link
	
	static hasMany=[users:User]
	
	static belongsTo=User

    	static constraints = {
		link nullable:false
    	}
}
