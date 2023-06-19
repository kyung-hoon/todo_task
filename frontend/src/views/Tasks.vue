<template>
  <div class="body">
    <Portal v-if="openModal" to="modal">
      <TaskModal
          ref="taskModal"
          :close="() => (openModal = false)"
          v-if="memberId !== null"
          :memberId="memberId"
          :key="memberId"
      ></TaskModal>
      <TaskModal
          ref="taskModal"
          :close="() => (openModal = false)"
          v-else
          :key="memberId"
      ></TaskModal>
    </Portal>
    <main class="main">
      <header class="header">
        <h1 class="title">Share TODO</h1>
        <div class="button-group">
          <Button @click.native.prevent="addTask">Add a new task</Button>
          <Button
              class="my-tasks-button"
              @click.native.prevent="fetchTasksForMemberId"
          >
            My Tasks
          </Button>
          <Button class="show-all-button" @click.native.prevent="showAll">
            Show All
          </Button>
          <Button
              class="manage-button"
              v-if="isAdmin"
              @click.native.prevent="manageTasks"
          >
            Manage
          </Button>
          <Button class="share-task-button" @click.native.prevent="openShareModal">
            Share My Task
          </Button>
        </div>
      </header>
      <TaskList v-if="tasks" :tasks="tasks"></TaskList>
      <p v-else>
        You do not have any tasks
        <button class="link" @click="addTask">add a new task</button>
      </p>
    </main>
    <ShareModal
        v-if="showShareModal"
        :shareLink="shareLink"
        @close="closeShareModal"
    />
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
import TaskList from "@/components/TaskList";
import Button from "@/components/Button";
import TaskModal from "@/components/Modal/TaskModal";
import Members from "@/views/members";
import ShareModal from "@/components/Modal/ShareModal";

export default {
  components: { Button, TaskList, TaskModal, Members,ShareModal },
  data() {
    return {
      openModal: false,
      memberId: null,
      showShareModal: false,
    };
  },
  computed: {
    ...mapState(["tasks"]),
    isAdmin() {
      return this.$store.state.isAdmin;
    },
    shareLink() {
      const memberId = this.$route.query.memberId;
      return `${window.location.origin}/share?memberId=${memberId}`;
    },
  },
  created() {
    this.$store.dispatch("getTasks");
    this.memberId = this.$route.query.memberId;
  },
  methods: {
    ...mapActions(["getMyTasks"]),
    addTask() {
      this.openModal = true;
    },
    fetchTasksForMemberId() {
      const memberId = this.$route.query.memberId;
      this.getMyTasks(memberId);
    },
    showAll() {
      this.$store.dispatch("getTasks");
    },
    manageTasks() {
      // 관리 작업 실행
      this.$router.push('/member')
    },
    openShareModal() {
      this.showShareModal = true;
    },
    closeShareModal() {
      this.showShareModal = false;
    },
  },
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
  line-height: 1;
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
  align-items: center;

  > * {
    margin-left: 1rem;
  }
}

.link {
  // link styles
}
</style>