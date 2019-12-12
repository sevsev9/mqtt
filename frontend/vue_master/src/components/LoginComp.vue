<template>
    <div>
        <Login></Login>
        <hr>
        <p>
            <router-link :to="{ name: 'register', params: { user: 'testuser' } }">
                <font-awesome-icon :icon="['fas', 'user-plus']"/>
                I don't have an account yet.
            </router-link>
        </p>
    </div>
</template>

<script>
    import Login from "@/components/Login";

    export default {
        name: "LoginComp",
        components: {Login}
    }
</script>

<style scoped>

</style>