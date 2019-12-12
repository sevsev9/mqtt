<template>
    <div>
        <b-card bg-variant="light" class="card" style="width: 34rem;" align="center">
            <div class="Data">
                <b-form-group
                        label-cols-sm="3"
                        label="Vorname:"
                        label-align-sm="right"
                        label-for="nested-vn"
                >
                    <b-form-input id="nested-vn"></b-form-input>
                </b-form-group>
                <b-form-group
                        label-cols-sm="3"
                        label="Benutzername:"
                        label-align-lg="right"
                        label-for="nested-name"
                >
                    <b-form-input id="nested-name"></b-form-input>
                </b-form-group>

                <b-form-group
                        label-cols-sm="3"
                        label="Passwort:"
                        label-align-sm="right"
                        label-for="nested-pw"
                >
                    <password v-model="password"></password>

                </b-form-group>

                <b-form-group
                        label-cols-sm="3"
                        label="E-Mail:"
                        label-align-sm="right"
                        label-for="nested-state"
                >
                    <b-form-input id="nested-state"></b-form-input>
                </b-form-group>
                <b-button v-model="registerbtn" class="btn btn-success"> Register</b-button>
            </div>
        </b-card>
    </div>
</template>

<script>
    import Password from 'vue-password-strength-meter'

    export default {
        name: "Register",
        components: { Password },
        data: () => ({
            password: null,
            registerbtn:null
        })
    }
</script>

<style scoped>
    .card{
        margin: 0 auto; /* Added */
        float: none; /* Added */
        margin-bottom: 10px; /* Added */
    }


</style>