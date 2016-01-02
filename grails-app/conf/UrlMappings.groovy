class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }
		
		"/user/$userId/cube"(controller: "user"){
			action = [GET:"listCubes",POST:"createCube"]
		}	
		"/user/$userId/content"(controller: "user"){
			action = [GET:"listContents",POST:"createContent"]
		}	
		"/user/$userId/cube/$cubeId/content"(controller: "user"){
			action = [POST:"addContentToCube"]
		}
		"/user/$userId/cube/$cubeId/content/$contentId"(controller: "user"){
			action = [DELETE:"deleteContentFromCube"]
		}
		"/user/$userId/cube/$cubeId"(controller: "user"){
			action = [DELETE:"deleteCube"]
		}
		"/user/$userId/cube/$cubeId/share"(controller: "user"){
			action = [POST:"shareCube"]
		}
		"/user/$userId/content/$contentId/share"(controller: "user"){
			action = [POST:"shareContent"]
		}

        "/"(view:"/index")
        "500"(view:'/error')
	}
}
