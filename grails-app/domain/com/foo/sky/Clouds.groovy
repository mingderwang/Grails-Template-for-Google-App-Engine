package com.foo.sky

import javax.jdo.annotations.*;
// import com.google.appengine.api.datastore.Key;

@PersistenceCapable(identityType = IdentityType.APPLICATION, detachable="true")
class Clouds {

	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	Long id

	@Persistent
	String tag

    static constraints = {
    	id( visible:false)
	}
}
