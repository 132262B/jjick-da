class HttpUtil {

    constructor() {
    }

    /**
     * http 기본 요청시 사용하는 메서드
     *
     * @param url {string}
     * @param type {string}
     * @param data {any[]} json
     * @param successFunction
     * @param compileFunction
     */
    defaultRequest(url, type, data, successFunction, compileFunction) {
        $.ajax({
            url: url,
            type: type,
            data: data,
            success: function (data) {
                successFunction(data);
            },
            error: function (err) {
                // 공통 에러 개발해야함
            },
            compile: function () {
                compileFunction();
            }
        });
    }

    /**
     * 에러 발생시 특정 error function을 작동하게 해줌.
     *
     * @param url {string}
     * @param type {string}
     * @param data {any[]} json
     * @param successFunction
     * @param errorFunction
     * @param compileFunction
     */
    errorControlRequest(url, type, data, successFunction, errorFunction, compileFunction) {
        $.ajax({
            url: url,
            type: type,
            data: data,
            success: function (data) {
                successFunction(data);
            },
            error: function (err) {
                errorFunction();
            },
            compile: function () {
                compileFunction();
            }
        });
    }

}