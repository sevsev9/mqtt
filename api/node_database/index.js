const mongoose = require('mongoose');
mongoose.connect('mongodb://ahit5:niceDayToday@docker.htl-wels.at:27017/5ahit', {useNewUrlParser: true});

var db = mongoose.connection;
db.on('error', console.error.bind(console, 'connection error:'));
db.once('open', function () {
    //Connected
});
var Sensordata = mongoose.Schema({
    time: Object,
    temperature: Number,
    humidity: Number,
    pressure: Number
});

var Sensor = mongoose.model('Sensor', Sensordata, 'Sensordata')

function Write(time, temperature, humidity, pressure) {

    var Sens = new Sensor({
        time: time.split('.'),
        temperature: temperature,
        humidity: humidity,
        pressure: pressure
    });

    Sens.save(function (err) {
        if (err) return console.error(err);
    })
}

function Read(){
    var res;
    Sensor.find().lean().exec(function (err, Sensordata) {
        return res.end(JSON.stringify(Sensordata));
    })
}


