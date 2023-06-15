import Vue from "vue";
import Router from "vue-router";
import Tasks from "@/views/Tasks.vue";
import NotFound from "@/views/NotFound.vue";
import Login from "@/views/Login.vue";
import Register from "@/views/Register";

Vue.use(Router);

const router = new Router({
  mode: "history",
  base: process.env.BASE_URL,
  routes: [
    {
      path: "/",
      name: "login",
      component: Login,
      meta: {
        title: "Login - pawaTask"
      }
    },
    {
      path: "/task",
      name: "task",
      component: Tasks,
      meta: {
        title: "Tasks - pawaTask"
      }
    },
    {
      path: '/register',
      name: 'Register',
      component: Register,
      meta: {
        title: "Register - pawaTask"
      }
    },
    {
      path: "/404",
      component: NotFound,
      meta: {
        title: "404 - pawaTask"
      }
    },
    { path: "*", redirect: "/404" }
  ]
});

router.beforeEach((to, _, next) => {
  document.title = to.meta.title;

  next();
});

export default router;
