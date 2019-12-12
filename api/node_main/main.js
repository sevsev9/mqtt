//EXPRESS

const express = require('express');
const app = express();

const bodyParser = require('body-parser');
app.use(bodyParser.urlencoded({ extended: true }));

app.get('/', (req, res) => res.sendFile('./auth.html', { root : __dirname}));

const port = process.env.PORT || 4000;
app.listen(port , () => console.log('App listening on port ' + port));

//PASSPORT

const passport = require('passport');
app.use(passport.initialize());
app.use(passport.session());

app.get('/success', (req, res) => res.send("Welcome "+req.query.username+"!!"));
app.get('/error', (req, res) => res.send("error logging in"));

passport.serializeUser(function(user, cb) {
  cb(null, user.id);
});

passport.deserializeUser(function(id, cb) {
  User.findById(id, function(err, user) {
    cb(err, user);
  });
});

//MONGO

const mongoose = require('mongoose');
mongoose.connect('mongodb://ahit5:niceDayToday@docker.htl-wels.at:27017/5ahit', {useNewUrlParser: true}  );

const Schema = mongoose.Schema;
const UserDetail = new Schema({
      username: String,
      password: String
    });
const UserDetails = mongoose.model('Users', UserDetail, 'Users');

//PASSPORT LOCAL AUTHENTICATION

const LocalStrategy = require('passport-local').Strategy;

passport.use(new LocalStrategy(
  function(username, password, done) {
      UserDetails.findOne({
        username: username
      }, function(err, user) {
        if (err) {
          return done(err);
        }

        if (!user) {
          return done(null, false);
        }

        if (user.password != password) {
          return done(null, false);
        }
        return done(null, user);
      });
  }
));

app.post('/',
  passport.authenticate('local', { failureRedirect: '/error' }),
  function(req, res) {
    res.redirect('/success?username='+req.user.username);
  });
