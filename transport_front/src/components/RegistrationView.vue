<template>
  <div  class="normal-margin">
    <h1>{{ $t('Register') }}</h1>
    <transition name="fade">
      <div v-if="show">
    <form class="container">
      <label for="uname"><b>{{ $t('Login') }}</b></label>
      <input type="text"   v-model="login" :placeholder="$t('Enter Login')"  name="login" required>

      <label for="psw"><b>{{ $t('Password') }}</b></label>
      <input type="password"  v-model="password" :placeholder="$t('Enter Password')" name="password" required>

      <button type="button" @click="submitForm" class="btn">{{ $t('Login') }}</button>
    </form>

    <select id="lang" @change="changeLanguage($event)">
      <option value="en">English</option>
      <option value="ru">Russian</option>
    </select>
      </div>
    </transition>
  </div>
</template>

<script>
import {url} from "@/main";
export default {
  mounted() {
    setTimeout(() => {
      this.show = true;
    }, 500);
  },
  name: 'RegistrationView',
  data() {
    return {
      show: false,
      login: '',
      password: '',
    };
  },
  methods: {
    submitForm() {
      // basic validation, you might want to use a library for this
      if (!this.login || !this.password) {
        alert('Please fill in both fields.');
        return;
      }
      // replace with actual API call
      console.log('Logging in with', this.login, this.password);
      // basic validation, you might want to use a library for this
      if (!this.login || !this.password) {
        alert('Please fill in both fields.');
        return;
      }

      this.$cookies.set('login', this.login);
      // API call
      console.log('Logging in with', this.login, this.password);
      this.$http.post(url + "/auth/register",  {
            login: this.login,
            password: this.password
          },
          {
            headers: {
              "Content-Type" : "application/json"
            }
          }
      ).then(response => {
        let token = response.data.token;
        this.$cookies.set('token', token);

        // localStorage.setItem('token', token); // not secure

        setTimeout(() => {
          this.show = false;
        }, 500);


        this.$router.push(this.$cookies.get('last_page') || "/drivers");
      }).catch((e) => {
        alert("Incorrect credits")
        console.log(e.toString())
      })
    },
    changeLanguage(event) {
      this.$i18n.locale = event.target.value;
    }
  },
};
</script>

<style scoped>
body {
  font-family: Arial, Helvetica, sans-serif;
}

.container {
  width: 300px;
  padding: 16px;
  background-color: white;
  margin: 0 auto;
  margin-top: 100px;
  border: 1px solid black;
  border-radius: 4px;
}

input[type=text], input[type=password] {
  width: 90%;
  padding: 15px;
  margin: 5px 0 22px 0;
  display: inline-block;
  border: none;
  background: #f1f1f1;
}

input[type=text]:focus, input[type=password]:focus {
  background-color: #ddd;
  outline: none;
}

.btn {
  background-color: #4CAF50;
  color: white;
  padding: 16px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
  width: 100%;
  opacity: 0.9;
}

.container .btn:hover {
  opacity: 1;
}

.fade-enter-active, .fade-leave-active {
  transition: opacity .5s;
}
.fade-enter, .fade-leave-to {
  opacity: 0;
}

.normal-margin {
  margin: 15pc;
}

</style>