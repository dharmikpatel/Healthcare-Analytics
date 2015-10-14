/**
 * render map data and disease introduction
 */
var ejs = require('ejs');
exports.showStateMap = function(req,res)
{
	 ejs.renderFile('views\\showmap.ejs',
				function(err, result) {
				if (!err) {res.end(result);}
				else {res.end('An error occurred');console.log(err);}
		});
}

exports.showMapData = function(req,res)
{

}
