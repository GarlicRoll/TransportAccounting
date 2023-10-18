<template>
  <div class="control-panel">
    <img alt="Vue logo" src="@/assets/logo.png">
      <router-link tag="span" class="router-link-exact-active" to="/drivers">{{ $t('Drivers') }}</router-link>
      <router-link tag="span" class="router-link-exact-active" to="/buses">{{ $t('Buses') }}</router-link>
      <router-link tag="span" class="router-link-exact-active" to="/schedule">{{ $t('Schedule') }}</router-link>
      <router-link tag="span" class="router-link-exact-active" to="/violations">{{ $t('Violations') }}</router-link>
    <div class="settings">
      <button type="button" class="btn" @click="logout">{{ $t('Logout') }}</button>
        <select v-model="selectedLanguage" @change="changeLanguage" class="btn">
          <option v-for="language in languages" :key="language.code" :value="language.code">{{language.name}}</option>
        </select>
    </div>
  </div>
</template>

<script>
import {url} from "@/main";

export default {
  data() {
    return {
      login: "",
      selectedLanguage: localStorage.getItem('language') || 'en', // Default selected language
      languages: [
        {code: 'en', name: 'English'},
        {code: 'ru', name: 'Русский'}
      ]
    }
  },
  methods: {
    logout() {
      this.login = this.$cookies.get('login');
        if (this.login === undefined || this.login == null) {
          this.$cookies.remove('token')
          this.$router.push('/auth');
        } else {
          // localStorage.setItem('token', '');
          this.$http.post(url + "/auth/logOut", {
                login: this.login,
              }
          ).then(() => {
            this.$cookies.remove('token')
            this.$router.push('/auth');
          })
        }
    },
    changeLanguage() {
      this.$i18n.locale = this.selectedLanguage;
      localStorage.setItem('language', this.selectedLanguage);
    }
  }
}
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
  display: inline;
  background-color: #42b983;
  color: white;
  padding: 16px 20px;
  margin: 10px 10px;
  border: none;
  cursor: pointer;
  width: 10pc;
  height: 5pc;
  opacity: 0.9;
  font-size: 24px;
}

.container .btn:hover {
  opacity: 1;
}
.control-panel {
  display: flex;
  justify-content: space-between;
  width: auto;
  padding: 10px 20px;
  background-color: #f8f9fa;
}

.control-panel{

  font-size: 24px;
}

.settings{
  display: flex;
  flex-direction: column;
}

.router-link-exact-active {
    margin: 70px 0px;
    font-size: 32px;
    height: 10px;
   cursor: pointer;
}

.fade-enter-active, .fade-leave-active {
  transition: opacity 0.5s;
}

.fade-enter, .fade-leave-to {
  opacity: 0;
}

</style>