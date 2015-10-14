
/**
 * Module dependencies.
 */

var express = require('express')
  , routes = require('./routes')
  , http = require('http')
  , path = require('path')
 // , mongoFetch = require('./routes/fetchdiseasedata')
  , homePage = require('./routes/homepage')
  , showMap = require('./routes/showmap')
  , sortableGraph = require('./routes/sortablegraph')
  , groupBar = require('./routes/groupbar')
  , pieChart = require('./routes/piechart');

var app = express();

// all environments
app.set('port', process.env.PORT || 2014);
app.set('views', __dirname + '/views');
app.set('view engine', 'ejs');
app.use(express.favicon());
app.use(express.logger('dev'));
app.use(express.bodyParser());
app.use(express.methodOverride());
app.use(app.router);
app.use(express.static(path.join(__dirname, 'public')));

app.configure(function () {
	app.use(express.json());
	app.use(express.urlencoded());
	app.use(express.methodOverride());																						 
	app.use(express.cookieParser("thissecretrocks"));
	app.use(express.cookieSession({ secret: 'xDDFsdfddsdfSDdbg', cookie: { maxAge: null }}));									
	app.use(app.router);
	app.use(express.static(path.join(__dirname, "public")));
	app.use(express.errorHandler({ dumpExceptions: true, showStack: true }));
});

// development only
if ('development' == app.get('env')) {
  app.use(express.errorHandler());
}

/*process.on('uncaughtException', function (err) {
    console.log(err);
});*/ 
app.get('/', routes.index);
app.get('/homepage',homePage.showHomePage);
app.get('/showmap', showMap.showStateMap);

app.get('/data',showMap.showMapData);

app.get('/sortablegraph',sortableGraph.showSortableGraph);
app.get('/sortablegraphdata',sortableGraph.showSortableGraphData);
app.get('/groupbar',groupBar.showGroupBar);
app.get('/groupbardata',groupBar.showGroupBarData);
app.get('/piechart',pieChart.showPieChart);
app.get('/piechartdata/:value',pieChart.showPieChartData);

http.createServer(app).listen(app.get('port'), function(){
  console.log('Express server listening on port ' + app.get('port'));
});
