package com.food_registry.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;


import com.food_registry.order.entity.Sequence;

//This method will generate sequence that is missing in MongoDB

@Service
public class SequenceGenerator {
	
//	Interface to handle findByAll, findOne, findByID, findByName, etc on mongoDB
//	Like how JPARepository handles it on SQL
	@Autowired
	private MongoOperations mongoOperations;
	
	public Integer generateNextOrderID() {
		
//		Remember collection has name = sequence in Sequence entity, so we're checking for id there
//		There we're fetching and increment it by 1
//		returnNew(true) says that the modified document should be returned as the result
//		upsert(true) says that if the document matching the query is not found, a new document should be created
//		Then we have entity class
		Sequence counter = mongoOperations.findAndModify(
				query(where("_id").is("sequence")),
				new Update().inc("sequence", 1),
				options().returnNew(true).upsert(true),
				Sequence.class);
		
		return counter.getSequence();
	}
}
