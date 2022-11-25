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
     */
    defaultRequest(url, type, data, successFunction) {
        $.ajax({
            url: url,
            type: type,
            data: JSON.stringify(data),
            dataType: "json",
            contentType: 'application/json',
            success: function (data) {
                successFunction(data);
            },
            error: function (err) {
                errorMessageToast(err.responseJSON.message);
            },
            complete: function () {
                // 공통 컴플리트 개발해야함.
            }
        });
    }

    /**
     * http 기본 요청시 사용하는 메서드
     *
     * @param url {string}
     * @param type {string}
     * @param data {any[]} json
     * @param successFunction
     * @param completeFunction
     */
    completeControlRequest(url, type, data, successFunction, completeFunction) {
        $.ajax({
            url: url,
            type: type,
            data: JSON.stringify(data),
            dataType: "json",
            contentType: 'application/json',
            success: function (data) {
                successFunction(data);
            },
            error: function (err) {
                errorMessageToast(err.responseJSON.message);
            },
            complete: function () {
                completeFunction();
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
     * @param completeFunction
     */
    errorControlRequest(url, type, data, successFunction, errorFunction, completeFunction) {
        $.ajax({
            url: url,
            type: type,
            data: JSON.stringify(data),
            dataType: "json",
            contentType: 'application/json',
            success: function (data) {
                successFunction(data);
            },
            error: function (err) {
                errorMessageToast(err.responseJSON.message);
            },
            complete: function () {
                completeFunction();
            }
        });
    }

    /**
     * 업로드 요청시 사용하는 메서드
     *
     * @param url {string}
     * @param type {string}
     * @param data {object} formData
     * @param successFunction
     * @param completeFunction
     */
    uploadRequest(url, type, data, successFunction) {
        $.ajax({
            enctype: 'multipart/form-data',
            processData: false,
            contentType: false,
            url: url,
            type: type,
            data: data,
            dataType: "json",
            success: function (data) {
                successFunction(data);
            },
            error: function (err) {
                errorMessageToast(err.responseJSON.message);
            },
            complete: function () {
                // 공통 컴플리트 개발해야함.
            }
        });
    }

}