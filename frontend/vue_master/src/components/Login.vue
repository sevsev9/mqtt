<template>
    <div class="login">
        <img alt="welcome" src="../assets/welcome.png">
        <br><br><br><br>
        <b-form @submit.prevent="onSubmit" @reset.prevent="onReset" style="padding-left: 30%; padding-right: 30%">
            <b-form-group
                    id="input-group-1"
                    label="Login"
                    label-for="input-1"
            >
                <b-form-input
                        id="input-1"
                        type="text"
                        required
                        placeholder="username.."
                        v-model="userData.name"
                ></b-form-input>
            </b-form-group>
            <b-form-group
                    id="input-group-2"
                    label-for="input-2"
            >
                <b-form-input
                        id="input-2"
                        type="password"
                        required
                        placeholder="password.."
                        v-model="userData.password"
                ></b-form-input>
            </b-form-group>
            <b-form-group
                    label="User or password not found!"
                    label-for="input-1"
                    style="color: red"
                    v-if="loginfailed"
            >
            </b-form-group>

            <b-button type="submit" variant="primary">Submit</b-button>
            <b-button class="ml-3" type="reset" variant="danger">Reset</b-button>
        </b-form>
    </div>
</template>

<script>
    const axios = require("axios").default
    const config = require("./Login-Config")

    export default {
        name: 'Login',
        data() {
            return {
                userData: {
                    name: "",
                    password: ""
                },
                loginfailed: false
            }
        },
        methods: {
            onSubmit() {
                this.loginfailed = false

                // eslint-disable-next-line no-console
                console.log("DATA: " + JSON.stringify(this.userData))
                // eslint-disable-next-line no-console
                console.log("Get data from " + config.connectTo())

                axios
                    .post(config.connectTo(), this.userData)
                    .then(response => {
                        // eslint-disable-next-line no-console
                        console.log("Authorization: " + response.data.authorization)

                        if (response.data.authorization == 200) {
                            this.$emit("login-success", this.userData)
                        } else if (response.data.authorization == 300) {
                            this.loginfailed = true
                        }

                    }).catch(err => {
                    // eslint-disable-next-line no-console
                    console.log(err)
                })

            },
            onReset() {
                this.userData.name = "";
                this.userData.password = "";
            }
        }
    }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
    h3 {
        margin: 40px 0 0;
    }

    ul {
        list-style-type: none;
        padding: 0;
    }

    li {
        display: inline-block;
        margin: 0 10px;
    }

    a {
        color: #42b983;
    }
</style>