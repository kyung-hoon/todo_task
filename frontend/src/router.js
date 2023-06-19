import Vue from "vue";
import Router from "vue-router";
import Tasks from "@/views/Tasks.vue";
import NotFound from "@/views/NotFound.vue";
import Login from "@/views/Login.vue";
import Register from "@/views/Register";
import TaskList from "@/components/TaskList";
import TaskModal from "@/components/Modal/TaskModal";
import Members from "@/views/members";
import members from "@/views/members";
import ShareTasks from "@/views/ShareTasks";

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
      path: "/share",
      name: "share",
      component: ShareTasks,
      meta: {
        title: "share tasks - pawaTask"
      }
    },
    {
      path: "/tasks",
      name: "tasks",
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
      path: '/member',
      name: 'members',
      component: members,
      meta: {
        title: "Members - pawaTask"
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
