<template>
  <div class="register-container">
    <h2>회원가입</h2>
    <form @submit.prevent="register" class="register-form">
      <div class="form-group">
        <label for="username">사용자 아이디:</label>
        <input type="text" id="username" v-model="username" class="form-control">
      </div>
      <div class="form-group">
        <label for="password">비밀번호:</label>
        <input type="password" id="password" v-model="password" class="form-control">
      </div>
      <div class="form-group">
        <label for="role">역할:</label>
        <select id="role" v-model="role" class="form-control">
          <option value="admin">admin</option>
          <option value="user">normal</option>
        </select>
      </div>
      <button type="submit" class="btn-register">회원가입</button>
    </form>
    <p>이미 계정이 있으신가요? <router-link to="/">로그인</router-link></p>

    <div v-if="errorMessage" class="error-message">{{ errorMessage }}</div>
    <div v-if="isRegistered" class="success-message">회원가입 성공!</div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      username: '',
      password: '',
      role: 'normal',
      errorMessage: '',
      isRegistered: false
    };
  },
  methods: {
    register() {
      const params = {
        memberId: this.username,
        password: this.password,
        role: this.role
      };
      if(params.memberId ==''){
        alert("아이디를 입력하여 주십시오")
        return;
      }if(params.password ==''){
        alert("비밀번호를 입력하여 주십시오")
        return;
      }if(params.role ==''){
        alert("역할을 선택하여 주십시오")
        return;
      }
      fetch('http://localhost:8080/api/member', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(params)
      })
          .then(response => {

                if (response.ok) {
                  this.isRegistered = true;
                  alert('회원가입 성공');
                  this.$router.push({path: '/'}); // 회원가입 성공 시 로그인 페이지로 이동
                } else if (response.status ===403){
                  alert('이미 존재하는 ID 입니다.');
                } else{
                  alert('회원가입 실패');
                }
              }
          )
          .catch(error => {
            console.error('회원가입 요청 에러:', error);
            alert('회원가입 요청 중 에러가 발생했습니다.');
          });
    }
  }
};
</script>

<style scoped>
.register-container {
  max-width: 400px;
  margin: 0 auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 5px;
}

.register-form {
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

.btn-register {
  background-color: #007bff;
  color: #fff;
  padding: 10px 20px;
  font-size: 16px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

.btn-register:hover {
  background-color: #0056b3;
}

.error-message {
  color: red;
  margin-top: 10px;
}

.success-message {
  color: green;
  margin-top: 10px;
}
</style>