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
            success: (data) => {
                successFunction(data);
            },
            error: (err) => {
                errorMessageToast(err.responseJSON.message);
            }
        });
    }

    /**
     * http 요청시 로딩바가 필요할때 사용하는 메서드
     *
     * @param url {string}
     * @param type {string}
     * @param data {any[]} json
     * @param successFunction
     */
    loadingRequest(url, type, data, successFunction) {
        $.ajax({
            url: url,
            type: type,
            data: JSON.stringify(data),
            dataType: "json",
            contentType: 'application/json',
            success: (data) => {
                successFunction(data);
            },
            error: (err) => {
                errorMessageToast(err.responseJSON.message);
            },
            complete: () => {
                closeLoadingBar();
            },
            beforeSend: () => {
                openLoadingBar();
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
            success: (data) => {
                successFunction(data);
            },
            error: (err) => {
                errorMessageToast(err.responseJSON.message);
            },
            complete: () => {
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
            success: (data) => {
                successFunction(data);
            },
            error: (err) => {
                errorMessageToast(err.responseJSON.message);
            },
            complete: () => {
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
            success: (data) => {
                successFunction(data);
            },
            error: (err) => {
                errorMessageToast(err.responseJSON.message);
            }
        });
    }


    /**
     * http 기본 요청
     * 1. 컨트롤러에서 PathVariable 로 데이터 받아올때 사용하는 GET 방식의 메서드
     * 2. 데이터를 전달할 필요가 없는 경우에 사용.
     *
     * @param url {string}
     * @param successFunction
     */
    noDataRequest(url, successFunction) {
        $.ajax({
            url: url,
            type: "GET",
            dataType: "json",
            contentType: 'application/json',
            success: (data) => {
                successFunction(data);
            },
            error: (err) => {
                errorMessageToast(err.responseJSON.message);
            }
        });
    }


}