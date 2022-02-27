import { Cookies } from "quasar";
import { checkJwt, jwtParse } from "../utils/index.js";
import axios from "../api/index.js";

export async function checkAuth(to, from, next) {
  // if (checkJwt(token)) {
  //   // access 토큰이 사용 가능하므로 통과
  //   next();
  // } else {
  //   // 서버로 보내서 access 토큰 다시 발급 받아야댐.
  //   console.log("Access 토큰 만료");
  //   try {
  //     const res = await axios.postAccessToken({});
  //     if (res.data) {
  //       console.log("test", res.data);
  //       // console.log("결과", JSON.parse(res.data));
  //       // console.log("access 토큰 발급", res.data);
  //       // Cookies.set("AccessToken", res.data);
  //     }
  //   } catch (err) {
  //     console.error(err);
  //   }
  // }

  next();
}
