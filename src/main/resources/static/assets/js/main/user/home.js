function loadDashboard() {

    httpUtil.loadingRequest('/api/statistics/user-dashboard', 'GET', null, (data) => {

        counter('userTotalCount', data.data.userTotalCount);
        counter('examTotalCount', data.data.examTotalCount);
        counter('questionTotalCount', data.data.questionTotalCount);
        counter('resultTotalCount', data.data.resultTotalCount);

    });
}

$(document).ready(function () {
    loadDashboard();
});