<template>
  <div>
    <ul class="list">
      <li v-for="(member) in members" :key="member.memberId" class="task">
        <span class="member-id">{{ member.memberId }}</span>
        <div class="panel">
          <div class="panel__role">
            <select v-model="member.roleType" @change="handleRoleChange(member)">
              <option value="ADMIN">ADMIN</option>
              <option value="NORMAL">NORMAL</option>
            </select>
          </div>
        </div>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  props: { members: { type: Array, required: true } },
  methods: {
    handleRoleChange(member) {
      this.$emit('roleChanged', member);
    },
  },
};
</script>

<style scoped lang="scss">
.list {
  margin: 0;
  padding: 0;
  list-style-type: none;
}

.task {
  display: flex;
  border-bottom: 1px solid #ccc;
  padding: 0.6rem 0;
  position: relative;
  justify-content: space-between;
  align-items: center;

  &:last-child {
    border-bottom: none;
    padding-bottom: 0;
  }
}

.panel {
  display: flex;
  align-items: center;
  flex-direction: column;

  .panel__actions {
    display: flex;
    align-items: center;
  }

  .panel__role {
    /* Add your styles for role */
  }

  @media (min-width: 600px) {
    .panel__role {
      /* Add your styles for role on larger screens */
    }
    flex-direction: row;
  }
}

.role {
  /* Add your styles for role */
}

.buttons {
  display: flex;
  justify-content: flex-end;
  margin-top: 1rem;

  .save-button {
    margin-right: 1rem;
  }
}
</style>