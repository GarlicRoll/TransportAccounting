<template>
  <div>
    <div v-if="isAuthorized">
      <h1>Drivers List</h1>
      <ul>
        <li v-for="driver in drivers" :key="driver.id">
          {{ driver.name }}
        </li>
      </ul>
    </div>
    <div v-else>
      <h1>You are not authorized to view this page.</h1>
    </div>
  </div>
</template>

<script>
import {url} from "@/main";

export default {
  data() {
    return {
      drivers: [],
      isAuthorized: false
    }
  },
  async created() {
    //Here you would check the user status
    const userToken = localStorage.getItem("token")
    //const userToken = this.$cookies.get('userToken')

    if(userToken){
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

</style>