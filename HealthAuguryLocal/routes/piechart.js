/**
 * displays pie chart values
 */
var MongoClient = require('mongodb').MongoClient
    , format = require('util').format;
var ejs = require('ejs');

exports.showPieChart = function(req,res){
	ejs.renderFile('views\\piechart.ejs',
			function(err, result) {
			if (!err) {res.end(result);}
			else {res.end('An error occurred');console.log(err);}
	});
}

exports.showPieChartData = function(req,res){
	var chartType = req.param('value');
	console.log('-------------------------------------'+chartType);
	MongoClient.connect('mongodb://127.0.0.1:27017/statedocuments', function(err, db) {
	 if(err) {throw err;}
	   var collection = db.collection('CALIFORNIA');
	   var dataForMap = '[';
	   var dataForMapEnd = ']';
	   var stateData = '';
	   var counter = 0;
	   var piechart = '';
	   collection.find().each(function(err, doc) {
	         if(doc != null){
	        		 stateData = JSON.parse(JSON.stringify(doc));
		        		  if(counter===2){			        		
		        			  piechart = piechart+'{"disease": "'+stateData.diseaseName+'","population": '+stateData.yearlyPrediction+'}';
		        			  var piechartdata = dataForMap+piechart+dataForMapEnd;
		        			  console.log(piechartdata);
		        			  res.send(piechartdata);
		        		  }
			        		  else{
			        		 piechart = piechart+'{"disease": "'+stateData.diseaseName+'","population": '+stateData.yearlyPrediction+'},';				        		 				        		 
		        		  }			
	        	 }	
	         counter++;
	       });
});
}