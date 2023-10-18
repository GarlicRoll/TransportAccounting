<template>
  <div>
    <ControlPanel/>
  </div>
</template>

<script>
import ControlPanel from "@/components/ControlPanel.vue";

export default {
  name: "ViolationsView",
  components : {
    ControlPanel
  },
  async created() {
    this.$cookies.set('last_page', "/violations");
  },
}
</script>

<style scoped>

</style>