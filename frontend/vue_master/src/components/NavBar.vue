<template>
    <b-navbar toggleable="lg" type="light" variant="light">
        <b-navbar-brand href="#">
            MQTT
        </b-navbar-brand>
        <b-navbar-toggle target="nav-collapse" data-target="#navbarSupportedContent">
        </b-navbar-toggle>
        <b-collapse id="nav-collapse" is-nav>
            <b-navbar-nav class="mr-auto">
                <router-link :to="{ name: 'statistics', params: { user: 'testuser' } }" class="m-2">
                    <font-awesome-icon :icon="['fas', 'chart-bar']"/>
                    Statistics
                </router-link>
                <router-link :to="{ name: 'alarmlist', params: { user: 'testuser' } }" class="m-2">
                    <font-awesome-icon :icon="['fas', 'bell']"/>
                    AlarmList
                </router-link>
                <router-link :to="{ name: 'messwerte', params: { user: 'testuser' } }" class="m-2">
                    <font-awesome-icon :icon="['fas', 'ruler-vertical']"/>
                    Messwerte
                </router-link>
                <router-link :to="{ name: 'login', params: { user: 'testuser' } }" class="m-2">
                    <font-awesome-icon :icon="['fas', 'sign-out-alt']"/>
                    Logout
                </router-link>

            </b-navbar-nav>
            <b-navbar-nav class="ml-auto nav-flex-icons">
                <b-nav-item :to="{name: 'myprofile'}">
                    <img src="https://mdbootstrap.com/img/Photos/Avatars/avatar-5.jpg"
                         class="rounded-circle z-depth-0"
                         alt="avatar image" height="35">
                </b-nav-item>
            </b-navbar-nav>
        </b-collapse>
    </b-navbar>
</template>

<script>
    export default {
        name: "NavBar",
        data() {
            return {

            }
        },
        methods: {

        },
        props: {

        }
    }
</script>

<style scoped>
</style>