<template>
  <div>
    <ControlPanel/>
  </div>
</template>

<script>
import ControlPanel from "@/components/ControlPanel.vue";

export default {
  name: "ScheduleView",
  components : {
    ControlPanel
  },
  async created() {
    this.$cookies.set('last_page', "/schedule");
  },
}
</script>

<style scoped>

</style>