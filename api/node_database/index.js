const mongoose = require('mongoose');
mongoose.connect('mongodb://ahit5:niceDayToday@docker.htl-wels.at:27017/5ahit', {useNewUrlParser: true});

var db = mongoose.connection;
db.on('error', console.error.bind(console, 'connection error:'));
db.once('open', function() {



});