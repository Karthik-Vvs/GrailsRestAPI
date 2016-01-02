package com.rest

import grails.converters.JSON

class UserController {

    	def index() { 
		def json=request.JSON
		User user=new User(name:json['name'],city:json['city']);
		user.save(flush:true)
		
		def map=[id:user.id,'name':user.name,'city':user.city]
		render map as JSON
	}
	
	def createCube() {
		def userId=params.userId
		def json=request.JSON
		User user=User.get(userId)
		Cube cube=new Cube(name:json['name'])
		cube.save(flush:true)
		
		user.addToCubes(cube)
		user.save(flush:true)
		
		def result=[id:cube.id,'name':cube.name,'user_id':userId]
		render result as JSON
	}
	
	def createContent() {
		def userId=params.userId
		def json=request.JSON
		User user=User.get(userId)
		Content content=new Content(link:json['link'])
		content.save(flush:true)
		
		user.addToContents(content)
		user.save(flush:true)
		
		def result=[id:content.id,'link':content.link,'user_id':userId]
		render result as JSON
	}
	
	def addContentToCube(){
		def userId=params.userId
		def cubeId=params.cubeId
		def json=request.JSON
		
		User user=User.get(userId)
		
		def hashSet=user.cubes.findAll{it.id as long==cubeId as long}
		Cube cube=hashSet.toArray()[0]
		
		Content content=Content.get(json['content_id'])
		cube.addToContents(content)
		cube.save(flush:true)
		
		def result=['id':cube.id,'cube_id':cube.id,'content_id':content.id]
		render result as JSON		
	}
	
	def deleteContentFromCube(){
		def userId=params.userId
		def cubeId=params.cubeId
		def contentId=params.contentId
		def json=request.JSON
		
		User user=User.get(userId)
		
		def cubeSet=user.cubes.findAll{it.id as long==cubeId as long}
		Cube cube=cubeSet.toArray()[0]
		
		def contentSet=cube.contents.findAll{it.id as long==contentId as long}
		Content content=contentSet.toArray()[0]
		
		cube.contents.remove(content)
		cube.users.each{
			it.contents.remove(content)
		}
		
		def result="Deleted content:"+contentId+" from cube:"+cubeId+" for users:"+cube.users*.id
		render result
	}
	
	def deleteCube(){
		def userId=params.userId
		def cubeId=params.cubeId
		def json=request.JSON
		
		User user=User.get(userId)
		
		def cubeSet=user.cubes.findAll{it.id as long==cubeId as long}
		Cube cube=cubeSet.toArray()[0]
		
		cube.users.each{
			it.cubes.remove(cube)
		}
		
		def result="Deleted cube:"+cubeId+" for users:"+cube.users*.id
		render result
	}
	
	def shareCube() {
		def userId=params.userId
		def cubeId=params.cubeId
		def json=request.JSON
		User user=User.get(userId)
		User toUser=User.get(json['user_id'])
		
		def cubeSet=user.cubes.findAll{it.id as long==cubeId as long}
		Cube cube=cubeSet.toArray()[0]
		toUser.addToCubes(cube)
		cube.contents.each {
			toUser.addToContents(it)
		}
		toUser.save(flush:true)
		
		def result=['id':cube.id,'cube_id':cube.id,'user_id':toUser.id]
		render result as JSON
		
	}
	
	def shareContent() {
		def userId=params.userId
		def contentId=params.contentId
		def json=request.JSON
		User user=User.get(userId)
		User toUser=User.get(json['user_id'])
		
		def contentSet=user.contents.findAll{it.id as long==contentId as long}
		Content content=contentSet.toArray()[0]
		println(content.id)
		toUser.addToContents(content)
		toUser.save(flush:true)
		
		def result=['id':content.id,'content_id':content.id,'user_id':toUser.id]
		render result as JSON
	}
	
	def listCubes(){
		def userId=params.userId
		User user=User.get(userId)
		def result=[]
		user.cubes.each {
			def cubeMap=['id':it.id,'name':it.name,'user_id':userId]
			result+=[cubeMap]
		}
		render result as JSON
	}
	
	def listContents(){
		def userId=params.userId
		User user=User.get(userId)
		def result=[]
		user.contents.each {
			def contentMap=['id':it.id,'link':it.link,'user_id':userId]
			result+=[contentMap]
		}
		render result as JSON
	}
}
