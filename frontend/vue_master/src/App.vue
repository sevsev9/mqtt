<template>
  <div id="app">
    <hr>
    <h1>Navbaaaar</h1>
    <router-link to="/"> Logout </router-link>
    <router-link to="/user/statistics"> Statistics </router-link>
    <router-link to="/user/alarmlist"> Alarm List </router-link>
    <router-link to="/user/messwerte"> Messwerte</router-link>
    <hr>
    <!-- Individual Content -->
    <router-view></router-view>

  </div>
</template>

<script>


export default {
  name: 'app',
  components: {

  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
