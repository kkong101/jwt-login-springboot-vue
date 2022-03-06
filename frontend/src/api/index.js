import axios from "axios";
import { Cookies } from "quasar";
import store from "../store/index.js";

export default {
  getConfigHeader() {
    const token = Cookies.get("AccessToken");
    const config = {
      headers: {
        Authorization: "Bearer " + token,
      },
    };
    return config;
  },

  async getAxios(url) {
    let res;

    try {
      res = await axios.get(url, this.getConfigHeader());
    } catch (e) {
      // 토큰이 만료 되었을때,
      if (e.request.status == 401) {
        console.log("access 토큰 재발급 요청 보내기 전.");

        const token_res = await this.postAccessToken({
          user_id: store.getters.getUserId,
        });

        console.log(
          "401번 받고 토큰 요청 받기 위해 요청 보낸 응답 ",
          token_res
        );

        if (token_res.data) {
          Cookies.set("AccessToken", token_res.data.data.token);
          res = await axios.get(url, this.getConfigHeader());
        }
      }
    }

    return res;
  },

  async postAxios(url, data) {
    const token = Cookies.get("AccessToken");
    const config = {
      headers: {
        Authorization: "Bearer " + token,
      },
    };

    let res;

    try {
      res = await axios.post(url, data, config);
    } catch (e) {
      // 토큰이 만료 되었을때,
      if (e.request.status == 401) {
        console.log("access 토큰 재발급 받아야댐.");
      }
    }

    return res;
  },

  /**
   * User API - start
   */

  async postSignUp(data) {
    const url = `/api/login/addUser`;
    return await this.postAxios(url, data);
  },

  async postLogin(data) {
    const url = `/api/login/checkUser`;
    return await this.postAxios(url, data);
  },

  async postIdcheck(data) {
    const url = `/api/login/getUser`;
    return await this.postAxios(url, data);
  },
  /**
   * User API - End
   */

  /**
   * Auth
   */

  async postAccessToken(data) {
    const url = `/api/auth/token/access`;
    return await this.postAxios(url, data);
  },

  /**
   * Auth - End
   */

  /**
   * Other Page API
   */

  async getMyPage() {
    const url = `/api/other/mypage`;
    return await this.getAxios(url);
  },
  async getAdminPage() {
    const url = `/api/other/admin`;
    return await this.getAxios(url);
  },

  /**
   * Other Page - end
   */
};
