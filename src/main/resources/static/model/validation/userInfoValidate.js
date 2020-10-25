/**
 * 檢查Email格式
 * @param email
 * @return {boolean}
 * @constructor
 */
function isEmail(email) {
    let regex = /^([a-zA-Z0-9_\.\-\+])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
    if(!regex.test(email)) {
        return false;
    }else{
        return true;
    }
}

/**
 * 檢查帳號格式、長度
 * @param account
 * @return {boolean}
 */
function correctUserName(userName) {
    if(userName.length >= 10 )
        return true;

    return false ;
}

/**
 * 檢查密碼格式、長度
 * @param password
 * @return {boolean}
 */
function correctPassword(password) {
    if(password.length >= 10 )
        return true;

    return false ;
}

