<template>
  <div id="app">
    <RouterView></RouterView>
  </div>
</template>

<script>
export default {
  name: 'app',
  components: {
  },
  methods:{
    successEvent(i){
      alert("name: " + i.name + " password: " + i.password)

      this.$router.push({name: 'statistics', params: { project: 'user_name' }})

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
}
</style>
