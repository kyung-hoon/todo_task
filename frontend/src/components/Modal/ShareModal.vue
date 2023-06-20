<template>
  <div class="share-modal">
    <div class="share-modal-content">
      <h2>Share Task</h2>
      <p>Copy and share the link below:</p>
      <div class="input-container">
        <input class="share-input" type="text" :value="shareLink" readonly>
        <button class="copy-button" @click="copyShareLink">Copy</button>
      </div>
      <p v-if="copied" class="copied-message">Copied done!</p>
      <button class="close-button" @click="closeShareModal">Close</button>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    shareLink: {
      type: String,
      required: true,
    },
  },
  data() {
    return {
      copied: false,
    };
  },
  methods: {
    copyShareLink() {
      const input = document.createElement("input");
      input.value = this.shareLink;
      document.body.appendChild(input);
      input.select();
      document.execCommand("copy");
      document.body.removeChild(input);

      this.copied = true;

      setTimeout(() => {
        this.copied = false;
      }, 3000);
    },
    closeShareModal() {
      this.$emit("close");
    },
  },
};
</script>

<style scoped>
.share-modal {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: rgba(0, 0, 0, 0.5);
  z-index: 9999;
}

.share-modal-content {
  background-color: #fff;
  padding: 2rem;
  border-radius: 8px;
  max-width: 700px;
  text-align: center;
}

.share-modal-content h2 {
  margin-top: 0;
  margin-bottom: 1.5rem;
  font-size: 1.8rem;
}

.share-modal-content p {
  margin-bottom: 1rem;
  font-size: 1.2rem;
}

.input-container {
  display: flex;
  align-items: center;
  margin-bottom: 1.5rem;
}

.share-input {
  flex-grow: 1;
  padding: 0.8rem;
  border: 1px solid #ddd;
  border-radius: 4px;
  font-size: 1.2rem;
}

.copy-button {
  margin-left: 1rem;
  padding: 0.8rem 1.5rem;
  background-color: #007bff;
  color: #fff;
  border: none;
  border-radius: 4px;
  font-size: 1.2rem;
  cursor: pointer;
  transition: background-color 0.3s;

&:hover {
   background-color: #0056b3;
 }
}

.close-button {
  padding: 0.8rem 1.5rem;
  background-color: #ccc;
  color: #fff;
  border: none;
  border-radius: 4px;
  font-size: 1.2rem;
  cursor: pointer;
  transition: background-color 0.3s;

  &:hover {
   background-color: #999;
 }
}
</style>