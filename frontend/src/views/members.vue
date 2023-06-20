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
        <h1 class="title">Member Management</h1>
        <div class="button-group">
          <Button
              class="back"
              @click.native.prevent="back"
          >
            back
          </Button>
          <Button
              class="save"
              @click.native.prevent="saveAllRoles"
          >
            Save All
          </Button>
        </div>
      </header>
      <MemberList v-if="members" :members="members" @roleChanged="updateRole"></MemberList>
      <p v-else>
        No Members
      </p>
    </main>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
import Button from "@/components/Button";
import MemberList from "@/components/MemberList";

export default {
  components: { Button, MemberList },
  data() {
    return {
      openModal: false,
      memberId: null,
    };
  },
  computed: {
    ...mapState(["tasks","members","memberId"]),
    isAdmin() {
      console.log(this.$store.state.isAdmin)
      return this.$store.state.isAdmin;
    },
  },
  created() {
    this.$store.dispatch("getMembers");
  },
  methods: {
    ...mapActions(["updateMembers"]),
    back() {
      const memberId = this.$store.state.memberId
      console.log(memberId)
      function isCurrentMember(element)  {
        if(element.memberId === memberId)  {
          return true;
        }
      }
      const currentMember =this.members.find(isCurrentMember)

      if(currentMember.roleType=="NORMAL"){
        this.$store.state.isAdmin=false
      }else{
        this.$store.state.isAdmin=true
      }
      this.$router.push({ path: 'tasks', query: { memberId } });

    },
    updateRole(member) {
      const updatedMember = {
        ...member,
        roleType: member.roleType
      };
      this.$store.commit("UPDATE_MEMBER",updatedMember)
    },

    saveAllRoles() {
      console.log("Save all roles:", this.members);
      this.members.forEach(member => {
        this.$store.dispatch("updateMembers",member)
      });
      alert('권한 변경 성공');
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
}

.back {
  margin-right: 1rem;
}

.save {
  margin-left: 1rem;
}

.link {
}
</style>