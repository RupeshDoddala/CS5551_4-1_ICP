/**
 * Created by Marmik on 04/10/2016.
 */
var express = require('express');
var app = express();
var request = require('request');
app.get('/getPlace', function (req, res) {
    var result = {
        'venue': []
    };
    console.log(req.query);
    var propertiesObject = { maxResults: '25', part: 'snippet', q: req.query.q, key: 'AIzaSyBZ3yV5xBi2yDA2yPVwaH_we8ECSZI_2Lc' }

    request({ url: 'https://www.googleapis.com/youtube/v3/search', qs: propertiesObject }, function (error, response, body) {
      
        //All is good. Print the body
        body = JSON.parse(body);
        console.log(body);
        res.contentType('application/json');
        res.send(JSON.stringify(body));
        res.end();
    });
    console.log(result);


})

var server = app.listen(3000, function () {
    // var host = server.address().address
    var port = server.address().port

    console.log("Example app listening at http://127.0.0.1:%s", port)
})