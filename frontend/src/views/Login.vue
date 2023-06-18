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
      memberId: '',
      password: '',
      errorMessage: '',
      isLoggedIn: false
    };
  },
  methods: {
    login() {
      const params = {
        memberId: this.username,
        password: this.password,
      };
      if(params.memberId ==''){
        alert("아이디를 입력하여 주십시오")
        return;
      }if(params.password ==''){
        alert("비밀번호를 입력하여 주십시오")
        return;
      }
      fetch('http://localhost:8080/api/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(params)
      })
          .then(response => {

                if (response.ok) {
                  return response.json()
                } else if (response.status ===400){
                  alert('아이디를 확인해 주세요');
                  return ;
                } else{
                  alert('비밀번호가 잘못되었습니다.');
                  return ;
                }
              }
          ).then(data =>{
            const memberId = data.memberId;
           this.$store.commit("SET_MEMBER_ID", memberId);
           if(data.roleType === 'ADMIN'){
             this.$store.commit("UPDATE_IS_ADMIN",true)
           }else{
             this.$store.commit("UPDATE_IS_ADMIN",false)
           }
           this.$router.push({ path: 'tasks', query: { memberId } });
           })
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