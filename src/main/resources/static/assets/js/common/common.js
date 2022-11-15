
/**
 * Element 를 던지면 해당 Element 를 리턴한다. 존재하지 않다면 null 을 리턴한다.
 * @param id
 */
function existId(id) {
    const elem = document.getElementById(id);
    return (!isEmpty(elem) ? elem : null);
}

/**
 * id 를 던지면 해당 Element 의 value 값을 리턴한다. 존재하지 않다면 '' 을 리턴한다.
 * @param id
 */
function existIdValue(id) {
    const elem = document.getElementById(id);
    return (!isEmpty(elem) ? elem.value : '');
}


/**
 * 빈 Element 체크하여 결과값을 리턴한다.
 * @param str
 * @return {boolean}
 */
function isEmpty(str) {
    return typeof str == "undefined" || str == null;
}

/**
 * 문자열이 빈 문자열인지 체크하여 결과값을 리턴한다.
 * @param str
 * @return {boolean}
 */
function isEmptyStr(str) {
    return typeof str == "undefined" || str == null || str === "";
}

/**
 * 문자열이 빈 문자열인지 체크하여 기본 문자열로 리턴한다.
 * @param str
 * @param defaultStr
 */
function nvl(str, defaultStr) {
    if (typeof str == "undefined" || str == null || str === "") str = defaultStr;

    return str;
}

/**
 * ErrorMessageToast 출력
 * @param errorMessage - 에러 메시지
 */
function errorMessageToast(errorMessage) {
    const errorMessageToast = existId('errorMessageToast')
    const errorMessageElem = existId('errorMessage');

    errorMessageElem.innerText = errorMessage;
    const toast = new bootstrap.Toast(errorMessageToast);
    toast.show();
}