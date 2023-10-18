<template>
  <div>
    <ControlPanel/>
  </div>
</template>

<script>
import ControlPanel from "@/components/ControlPanel.vue";

export default {
  name: "BusesView",
  components : {
    ControlPanel
  },
  async created() {
    this.$cookies.set('last_page', "/buses");
  },
}
</script>

<style scoped>

</style>