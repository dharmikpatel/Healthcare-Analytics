/**
 * render home page
 */
var ejs = require('ejs');

exports.showHomePage = function(req,res)
{
	 ejs.renderFile('views\\homepage.ejs',
				function(err, result) {
				if (!err) {res.end(result);}
				else {res.end('An error occurred');console.log(err);}
		});
}
