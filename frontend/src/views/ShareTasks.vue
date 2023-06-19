<template>
  <div class="body">
    <main class="main">
      <header class="header">
        <h1 class="title">Share TODO</h1>
      </header>
      <TaskList v-if="tasks" :tasks="tasks"></TaskList>
      <p v-else>
        You do not have any tasks
      </p>
    </main>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
import TaskList from "@/components/TaskList";
import Button from "@/components/Button";
import TaskModal from "@/components/Modal/TaskModal";
import Members from "@/views/members";

export default {
  components: {  TaskList },
  data() {
    return {
      memberId: null,
    };
  },
  computed: {
    ...mapState(["tasks"]),
  },
  created() {
    this.getMyTasks(this.$route.query.memberId)
  }
};
</script>

<style lang="scss" scoped>
@import "../styles/variables";
@import "../styles/common";

.body {
  box-sizing: border-box;
  padding: 1.2rem;
}
.main {
  margin: 0 auto;
  background: $white;
  border: 1px solid $gray;
  padding: 1.5rem 1.3rem 1rem;

  @media (min-width: 600px) {
    width: 600px;
  }
}
.title {
  margin: 0;
  font-size: 1.5rem;
  line-height: 1px;
}
.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  border-bottom: 1px solid $gray;
  padding-bottom: 0.8rem;
}

.button-group {
  display: flex;

  .my-tasks-button {
    margin-left: 1rem;
  }

  .manage-button {
    margin-left: 1rem;
  }
}

.link {
}
</style>