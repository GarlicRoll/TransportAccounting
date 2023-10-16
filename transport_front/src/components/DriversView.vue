<template>
  <div>
    <div v-if="isAuthorized">
      <ControlPanel/>
      <h1>{{ $t('Drivers') }}</h1>
        <ul class="beautiful-list">
          <li v-for="(item, index) in drivers" :key="index">
            {{item.name}} {{ $t('has been driving for') }} {{item.experience}} {{ $t('years') }}
          </li>
        </ul>
      <form class="container" >

      <input type="text" v-model="userName" :placeholder="$t('Enter Name')" name="name" required>
      <input type="text" v-model="experience" :placeholder="$t('Enter Experience')" name="experience" required>
      <input type="text" v-model="licenseClass" :placeholder="$t('Enter Class')" name="licenseClass" required>
        <button type="button" class="btn" @click="addDriver">{{ $t('Add driver') }}</button>
      </form>
    </div>
    <div v-else>
      <h1>{{ $t('You are not authorized to view this page') }}.</h1>
    </div>
  </div>
</template>

<script>
import {url} from "@/main";
import ControlPanel from "@/components/ControlPanel.vue";
export default {
  components : {
    ControlPanel
  },
  data() {
    return {
      userName: "",
      experience: null,
      licenseClass: "",
      drivers: [],
      isAuthorized: false
    }
  },
  methods: {
    addDriver() {
      const userToken = this.$cookies.get('token')
      this.$http.post(url + "/drivers",  {
            name : this.userName,
            experience : this.experience,
            license_class: this.licenseClass
          }, {
              headers: {'Authorization': `Bearer ${userToken}`}
          }
      ).then(() => {
        this.getDrivers()
        this.userName = ""
        this.licenseClass = ""
        this.experience = null
      }).catch((e) => {
        alert(e.toString())
        console.log(userToken)
      })
    },
    getDrivers() {
        this.$http.get(url + "/drivers")
            .then(response => {
              const drivers = response && response.data ? response.data : []
              this.drivers = drivers.map(r => {
                return {
                  id: r.id,
                  name: r.name,
                  experience: r.experience,
                  licenseClass: r.licenseClass
                }
              })
            }).catch(e => {
          console.log(e)
        })
    }
  },
  async created() {
    //Here you would check the user status
    //const userToken = localStorage.getItem("token")
    const userToken = this.$cookies.get('token')

    if(userToken){ // to not do more requests to the server
      try {
        const driversResponse = await this.$http.get(url + "/drivers", {headers: {'Authorization': `Bearer ${userToken}`}})
        this.drivers = driversResponse.data
        this.isAuthorized = true
      } catch(error) {
        console.log('An error occurred:', error)
      }
    }
  }
}
</script>

<style scoped>
.beautiful-list {
  margin: auto;
  width: 50%;
  list-style-type: none;
  padding: 0;
  box-shadow: 0px 0px 10px rgba(0,0,0,0.15);
}

.beautiful-list li {
  padding: 15px;
  background-color: #f8f8f8;
  border-bottom: 1px solid #ddd;
  font-size: 18px;
  color: #333;
}

.beautiful-list li:last-child {
  border-bottom: none;
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
  opacity: 0;
  transition: opacity 0.5s ease-in-out;
}
</style>