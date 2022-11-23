let b = ["2022-10-01", "2022-10-02", "2022-10-03", "2022-10-04", "2022-10-05", "2022-10-06", "2022-10-07", "2022-10-08", "2022-10-09", "2022-10-10"];

let dashBoardData = {
    "newUser": {
        chart: {
            id: 'sparkline1',
            group: 'sparklines',
            type: 'area',
            height: 160,
            sparkline: {
                enabled: true
            },
        },
        stroke: {
            curve: 'straight'
        },
        fill: {
            opacity: 1,
        },
        series: [{
            name: '일일 신규이용자',
            data: []
        }],
        labels: [],
        yaxis: {
            min: 0
        },
        xaxis: {
            type: 'datetime',
        },
        colors: ['#DCE6EC'],
        title: {
            text: '신규이용자(일일)',
            offsetX: 10,
            style: {
                fontSize: '24px',
                cssClass: 'apexcharts-yaxis-title'
            }
        },
    },
    "examResultSubmitNumber": {
        chart: {
            id: 'sparkline1',
            group: 'sparklines',
            type: 'area',
            height: 160,
            sparkline: {
                enabled: true
            },
        },
        stroke: {
            curve: 'straight'
        },
        fill: {
            opacity: 1,
        },
        series: [{
            name: '제출수',
            data: [10, 41, 35, 51, 49, 62, 69, 91, 148, 62]
        }],
        labels: b,
        yaxis: {
            min: 0
        },
        xaxis: {
            type: 'datetime',
        },
        colors: ['#DCE6EC'],
        title: {
            text: '시험 제출수',
            offsetX: 10,
            style: {
                fontSize: '24px',
                cssClass: 'apexcharts-yaxis-title'
            }
        },
    }
}

function loadDashBoard() {


    httpUtil.defaultRequest(`/api/admin/dashBoard`, 'GET', null, function (data) {

        // 신규 사용자(일일) 통계
        data.data.newUser.forEach((i) => {
            dashBoardData.newUser.series[0].data.push(i.count);
            dashBoardData.newUser.labels.push(i.date);
        })

        const newUser = new ApexCharts(existId('newUser'), dashBoardData.newUser);
        newUser.render();

    });


    const examResultSubmitNumber = new ApexCharts(existId('examResultSubmitNumber'), dashBoardData.examResultSubmitNumber);
    examResultSubmitNumber.render();

}

$(document).ready(function () {
    loadDashBoard();
});