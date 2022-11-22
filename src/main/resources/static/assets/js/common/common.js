
/**
 * id 를 던지면 해당 Element 를 리턴한다. 존재하지 않다면 null 을 리턴한다.
 * @param id
 */
function existId(id) {
    const elem = document.getElementById(id);
    return (!isEmpty(elem) ? elem : null);
}

/**
 * name 을 던지면 해당 Element 를 리턴한다. 존재하지 않다면 null 을 리턴한다.
 * @param name
 */
function existName(name) {
    const elem = document.getElementsByName(name);
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

/**
 * WarningMessageToast 출력
 * @param warningMessage - 경고 메시지
 */
function warningMessageToast(warningMessage) {
    const warningMessageToast = existId('warningMessageToast')
    const warningMessageElem = existId('warningMessage');

    warningMessageElem.innerText = warningMessage;
    const toast = new bootstrap.Toast(warningMessageToast);
    toast.show();
}

/**
 * SuccessMessageToast 출력
 * @param successMessage - 성공 메시지
 */
function successMessageToast(successMessage) {
    const successMessageToast = existId('successMessageToast')
    const successMessageElem = existId('successMessage');

    successMessageElem.innerText = successMessage;
    const toast = new bootstrap.Toast(successMessageToast);
    toast.show();
}

function cutTime(date) {
    return date.substr(0,10);
}

/**
 * 숫자만 입력가능하게 막아줌.
 */
function onchangeNum(target){
    const regex = /[^0-9]/g;
    target.value = target.value.replace(regex,"");
}
/**
 * 1~100 입력가능하게 막아줌.
 */
function positiveNumber(target) {
    if(target.value < 1){
        target.value = "";
    }
    if(target.value > 100){
        target.value = 100;
    }

}