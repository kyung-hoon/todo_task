import Vue from "vue";
import Vuex from "vuex";
import mutations from "./mutations";
import actions from "./actions";
import createPersistedState from "vuex-persistedstate";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    members: [],
    tasks: [],
    memberId: "",
    isAdmin: false
  },
  mutations,
  actions,
  plugins: [createPersistedState()]
});
