<template>
  <div class="login-container">
    <h2>로그인</h2>
    <form @submit.prevent="login" class="login-form">
      <div class="form-group">
        <label for="username">사용자명:</label>
        <input type="text" id="username" v-model="username" class="form-control">
      </div>
      <div class="form-group">
        <label for="password">비밀번호:</label>
        <input type="password" id="password" v-model="password" class="form-control">
      </div>
      <button type="submit" class="btn-login">로그인</button>
    </form>
    <p>계정이 없으신가요? <router-link to="/register">회원가입</router-link></p>

    <div v-if="errorMessage" class="error-message">{{ errorMessage }}</div>
    <div v-if="isLoggedIn" class="success-message">로그인 성공!</div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      username: '',
      password: '',
      errorMessage: '',
      isLoggedIn: false
    };
  },
  methods: {
    login() {
      const params = new URLSearchParams();
      params.append('id', this.username);
      params.append('password', this.password);

      fetch(`http://localhost:8080/login?${params.toString()}`)
          .then(response => response.json())
          .then(data => {
            if (data.success) {
              // 로그인 성공 시 Task 페이지로 이동
              this.$router.push({ name: 'Task' });
            } else {
              alert('로그인 실패');
            }
          })
          .catch(error => {
            console.error('로그인 요청 에러:', error);
            alert('로그인 요청 중 에러가 발생했습니다.');
          });
    }
  }
};
</script>


<style scoped>
.login-container {
  max-width: 400px;
  margin: 0 auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 5px;
}

.login-form {
  margin-bottom: 20px;
}

.form-group {
  margin-bottom: 10px;
}

.label {
  display: block;
  font-weight: bold;
}

.form-control {
  width: 100%;
  padding: 8px;
  font-size: 16px;
  border: 1px solid #ccc;
  border-radius: 5px;
}

.btn-login {
  background-color: #007bff;
  color: #fff;
  padding: 10px 20px;
  font-size: 16px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

.btn-login:hover {
  background-color: #0056b3;
}

.error-message {
  color: red;
  margin-top: 10px;
}
</style>