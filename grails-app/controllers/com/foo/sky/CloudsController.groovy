package com.foo.sky

import com.google.appengine.api.datastore.*
class CloudsController {

	def persistenceManager
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
		def query = persistenceManager.newQuery( Clouds )
		def  cloudsInstanceList = query.execute()
		def total = 0
		if(  cloudsInstanceList &&  cloudsInstanceList.size() > 0){
			total =  cloudsInstanceList.size()
		}
		[  cloudsInstanceList :  cloudsInstanceList,  cloudsInstanceTotal: total ]
    }

    def show = {
	    def cloudsInstance = persistenceManager.getObjectById( Clouds.class, Long.parseLong( params.id )  )
        if(!cloudsInstance) {
            flash.message = "Clouds not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ cloudsInstance : cloudsInstance ] }
    }

    def delete = {
	    def cloudsInstance = persistenceManager.getObjectById( Clouds.class, Long.parseLong( params.id )  )
        if(cloudsInstance) {
            try {
                persistenceManager.deletePersistent(cloudsInstance)
                flash.message = "Clouds ${params.id} deleted"
                redirect(action:list)
            }
            catch(Exception e) {
                flash.message = "Clouds ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "Clouds not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
	    def cloudsInstance = persistenceManager.getObjectById( Clouds.class, Long.parseLong( params.id )  )
		if(!cloudsInstance) {
            flash.message = "Clouds not found with id ${params.id}"
            redirect(action:list)
        }
        else {
			cloudsInstance = persistenceManager.detachCopy( cloudsInstance )    
        	return [ cloudsInstance : cloudsInstance ]
        }
    }

    def update = {
	 	def cloudsInstance = persistenceManager.getObjectById( Clouds.class, Long.parseLong( params.id )  )
    
    	if(cloudsInstance) {
            cloudsInstance.properties = params
            if(!cloudsInstance.hasErrors()){
	
				try{
					persistenceManager.makePersistent(cloudsInstance)
				} catch( Exception e ){
				   	render(view:'edit',model:[cloudsInstance:cloudsInstance])
				}finally{
					flash.message = "Clouds ${params.id} updated"
	                redirect(action:show,id:cloudsInstance.id)
				}        
 			}
            else {
                render(view:'edit',model:[cloudsInstance:cloudsInstance])
            }
        }
        else {
            flash.message = "Clouds not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def create = {
        def cloudsInstance = new Clouds()
        cloudsInstance.properties = params
        return ['cloudsInstance':cloudsInstance]
    }

    def save = {
        def cloudsInstance = new Clouds(params)
		if(!cloudsInstance.hasErrors() ) {
			try{
				persistenceManager.makePersistent(cloudsInstance)
			} finally{
				flash.message = "Clouds ${cloudsInstance.id} created"
				redirect(action:show,id:cloudsInstance.id)	
			}
		}
   
		render(view:'create',model:[cloudsInstance:cloudsInstance])
        
    }
}
