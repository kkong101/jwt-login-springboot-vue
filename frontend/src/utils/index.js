export const checkJwt = (token) => {
  let isToken = true;
  if (token == null || token == "" || token == "undefined") {
    isToken = false;
  }
  try {
    if (isToken === true) {
      const decodedToken = jwtParse(token);
      const dateNow = Date.now();
      const expiredDate = decodedToken.exp * 1000;
      // 만료 날짜 지나면 false
      console.log("expiredDate", expiredDate, "now", dateNow);
      console.log(new Date(expiredDate));
      if (expiredDate < dateNow) return false;
      return true;
    }
    return false;
  } catch (e) {
    console.error(e);
    return false;
  }
};

export const jwtParse = (token) => {
  const base64Payload = token.split(".")[1];
  const decodedToken = JSON.parse(window.atob(base64Payload));
  return decodedToken;
};
