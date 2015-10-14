/**
 * Fetches data for selected disease
 */
var MongoClient = require('mongodb').MongoClient
    , format = require('util').format;
var ejs = require('ejs');

/*exports.showcentrioddata = function(req,res){
	var dataForMap = '{"type": "FeatureCollection","features": [';
	var dataForMapEnd = ']}';
	var features = '';
	MongoClient.connect('mongodb://127.0.0.1:27017/diseasedocuments', function(err, db) {
		   if(err) {throw err;}
		   var collection = db.collection('Hepatitis-type A');
		   var diseaseData = '';
		   var counter = 0;
		   //var extra = '{"type": "Feature","id": "11","geometry": {"type": "Point","coordinates": [-77.014001,38.910092]},"properties": {"name": "District of Columbia  ","population": 572059}}';
		   collection.find().each(function(err, doc) {
		         if(doc != null){
		        	 if(counter<1){
		        		 diseaseData = JSON.parse(JSON.stringify(doc));			        	  
			        	  for(var iterator=0;iterator<diseaseData.stateWiseData.length;iterator++){
			        		  if(iterator===diseaseData.stateWiseData.length-1){			        		
			        			  features = features+'{"type": "Feature","id": "'+diseaseData.stateWiseData[iterator].id+'","geometry": {"type": "Point","coordinates": ['+diseaseData.stateWiseData[iterator].longitude+','+diseaseData.stateWiseData[iterator].latitude+']},"properties": {"name": "'+diseaseData.stateWiseData[iterator].stateName+'","population": '+diseaseData.stateWiseData[iterator].weeklyData+'}}';
			        		  }
				        		  else{
			        			  features = features+'{"type": "Feature","id": "'+diseaseData.stateWiseData[iterator].id+'","geometry": {"type": "Point","coordinates": ['+diseaseData.stateWiseData[iterator].longitude+','+diseaseData.stateWiseData[iterator].latitude+']},"properties": {"name": "'+diseaseData.stateWiseData[iterator].stateName+'","population": '+diseaseData.stateWiseData[iterator].weeklyData+'}},';  
			        		  }			        		  
			        	  }		 
		        	 }	
		        	 var datatoshow = dataForMap+features+dataForMapEnd;
		        	 //var jsonobj = JSON.parse(datatoshow);
		        	 counter++; 
		          }		         
		       });
	 });
}*/

exports.showGroupBar = function(req,res){
	
	ejs.renderFile('views\\groupbar.ejs',
			function(err, result) {
			if (!err) {res.end(result);}
			else {res.end('An error occurred');console.log(err);}
	});
}

exports.showGroupBarData = function(req,res){
	var dataForMap = '[';
	var dataForMapEnd = ']';
	var groupStack = '';
	MongoClient.connect('mongodb://127.0.0.1:27017/diseasedocuments', function(err, db) {
		   if(err) {throw err;}
		   var collection = db.collection('Hepatitis-type A');
		   var diseaseData = '';
		   var counter = 0;
		   collection.find().each(function(err, doc) {
		         if(doc != null){
		        	 if(counter<1){
		        		 diseaseData = JSON.parse(JSON.stringify(doc));
		        		 var stateHighest = diseaseData.diseaseHighest[0].stateName;
		        		 var stateLowest = diseaseData.diseaseLowest[0].stateName;		        	
			        	  for(var iterator=0;iterator<diseaseData.stateWiseData.length;iterator++){
			        		  if(diseaseData.stateWiseData[iterator].stateName==stateHighest)
			        		  {
			        			  groupStack = groupStack+'{"State": "'+diseaseData.stateWiseData[iterator].stateName+'","previous year": "'+diseaseData.stateWiseData[iterator].statePreviousYear+'","current year": "'+diseaseData.stateWiseData[iterator].stateCurrentYear+'","next year": "'+diseaseData.stateWiseData[iterator].stateYearPrediction+'"},'
			        			  console.log('highest'+stateHighest);
			        		  }
			        		  if(diseaseData.stateWiseData[iterator].stateName==stateLowest)
			        		  {
			        			  groupStack = groupStack+'{"State": "'+diseaseData.stateWiseData[iterator].stateName+'","previous year": "'+diseaseData.stateWiseData[iterator].statePreviousYear+'","current year": "'+diseaseData.stateWiseData[iterator].stateCurrentYear+'","next year": "'+diseaseData.stateWiseData[iterator].stateYearPrediction+'"}';
			        			  console.log('highest'+stateLowest); 
			        		  }
			        	  }		 
		        	 }	
		        	 var groupBarData = dataForMap+groupStack+dataForMapEnd;
		        	 console.log(groupBarData);
		        	 counter++; 
		        	 res.send(groupBarData);
		          }		         
		       });
	 });	
}