<template>
  <div id="app">
    <!--<Chart :oildata="oildata"/>-->
    <SimpleChart/>
  </div>
</template>

<script>
// import Chart from "./components/Chart";
import SimpleChart from "./components/SimpleChart";

export default {
  name: 'app',
  components: {
    SimpleChart
    // Chart
  }, data() {
    return {
      oildata: [
        {
          "I": 1,
          "ti": "2019-04-20",
          "te": 10.23,
          "p": 5.49
        }, {
          "I": 1,
          "ti": "2019-04-21",
          "te": 10.5,
          "p": 5.6
        }, {
          "I": 1,
          "ti": "2019-04-22",
          "te": 10.9,
          "p": 5.8
        }, {
          "I": 1,
          "ti": "2019-04-23",
          "te": 11.3,
          "p": 6.0
        }, {
          "I": 1,
          "ti": "2019-04-24",
          "te": 11.7,
          "p": 6.2
        }, {
          "I": 1,
          "ti": "2019-04-25",
          "te": 10.23,
          "p": 5.49
        }
      ]
    }
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
