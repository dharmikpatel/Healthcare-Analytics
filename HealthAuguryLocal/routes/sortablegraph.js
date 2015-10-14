/**
 * show sortable graph..
 */
var ejs = require('ejs');
var MongoClient = require('mongodb').MongoClient
, format = require('util').format;

exports.showSortableGraph = function(req,res){
	
	ejs.renderFile('views\\sortablegraph.ejs',
			function(err, result) {
			if (!err) {res.end(result);}
			else {res.end('An error occurred');console.log(err);}
	});
}

exports.showSortableGraphData = function(req,res){
	var dataForMap = '[';
	var dataForMapEnd = ']';
	var sortableGraph = '';
	MongoClient.connect('mongodb://127.0.0.1:27017/diseasedocuments', function(err, db) {
		   if(err) {throw err;}
		   var collection = db.collection('Hepatitis-type A');
		   var diseaseData = '';
		   var counter = 0;
		   collection.find().each(function(err, doc) {
		         if(doc != null){
		        	 if(counter<1){
		        		 diseaseData = JSON.parse(JSON.stringify(doc));	        	
			        	  for(var iterator=0;iterator<diseaseData.stateWiseData.length;iterator++){
			        		  if(iterator===diseaseData.stateWiseData.length-1){			        		
			        			  sortableGraph = sortableGraph+'{"state": "'+diseaseData.stateWiseData[iterator].stateName+'","frequency": "'+diseaseData.stateWiseData[iterator].weeklyData+'"}';			        			  
			        		  }
				        		  else{
				        		  sortableGraph = sortableGraph+'{"state": "'+diseaseData.stateWiseData[iterator].stateName+'","frequency": "'+diseaseData.stateWiseData[iterator].weeklyData+'"},';				        		 				        		 
			        		  }		
			        	  }		 
		        	 }	
		        	 var sortableData = dataForMap+sortableGraph+dataForMapEnd;
		        	 console.log(sortableData);
		        	 counter++; 
		        	 res.send(sortableData);	
		          }		         
		       });
	 });	
}