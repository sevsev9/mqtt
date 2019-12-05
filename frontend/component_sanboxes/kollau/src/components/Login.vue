<template>
    <div class="hello">
        <img alt="welcome" src="../assets/welcome.png">
        <br><br><br><br>
        <b-form @submit.prevent="onSubmit" @reset.prevent="onReset">
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

            <b-button type="submit" variant="primary">Submit</b-button>
            <b-button class="ml-3" type="reset" variant="danger">Reset</b-button>

        </b-form>
        <br><br><br><br>
        Debug: {{debugvalue}}<br>
        n: {{userData.name}}<br>
        pw: {{userData.password}}<br>

    </div>
</template>

<script>
    var axios = require("axios")

    export default {
        name: 'Login',
        props: {
            msg: String
        },
        data() {
            return {
                userData: {
                    name: "",
                    password: ""
                },
                debugvalue: 0
            }
        },
        methods: {
            onSubmit() {
                axios
                    .post("/login", this.userData)
                    .then(response => {
                        // eslint-disable-next-line no-console
                        console.log(response)
                    }).catch(err => {
                    // eslint-disable-next-line no-console
                        console.log(err)
                })
                this.debugvalue = 22
            },
            onReset() {
                this.userData.name = "";
                this.userData.password = "";
                this.debugvalue = 33
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