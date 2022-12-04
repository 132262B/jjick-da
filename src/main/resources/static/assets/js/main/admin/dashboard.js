let b = ["2022-10-01", "2022-10-02", "2022-10-03", "2022-10-04", "2022-10-05", "2022-10-06", "2022-10-07", "2022-10-08", "2022-10-09", "2022-10-10"];

let dashboardData = {
    "newUsersCountByDate": {
        chart: {
            id: 'sparkline1',
            group: 'sparklines',
            type: 'area',
            height: 200,
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
            height: 200,
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
    },
    "test1chart": {
        series: [44, 55, 13, 43, 22],
        chart: {
            width: 325,
            type: 'pie',
        },
        labels: ['Team A', 'Team B', 'Team C', 'Team D', 'Team E'],
        responsive: [{
            breakpoint: 480,
            options: {
                chart: {
                    width: 150
                },
                legend: {
                    position: 'bottom'
                }
            }
        }]
    },
    "test2chart": {
        series: [{
            name: 'series1',
            data: [31, 40, 28, 51, 42, 109, 100]
        }, {
            name: 'series2',
            data: [11, 32, 45, 32, 34, 52, 41]
        }],
        chart: {
            height: 350,
            type: 'area'
        },
        dataLabels: {
            enabled: false
        },
        stroke: {
            curve: 'smooth'
        },
        xaxis: {
            type: 'datetime',
            categories: ["2018-09-19T00:00:00.000Z", "2018-09-19T01:30:00.000Z", "2018-09-19T02:30:00.000Z", "2018-09-19T03:30:00.000Z", "2018-09-19T04:30:00.000Z", "2018-09-19T05:30:00.000Z", "2018-09-19T06:30:00.000Z"]
        },
        tooltip: {
            x: {
                format: 'dd/MM/yy HH:mm'
            },
        },
    }
}

function loadDashboard() {

    httpUtil.loadingRequest('/api/statistics/admin-dashboard', 'GET', null, (data) => {

        // 신규 사용자(일일) 통계
        data.data.newUsersCountByDate.forEach((i) => {
            dashboardData.newUsersCountByDate.series[0].data.push(i.count);
            dashboardData.newUsersCountByDate.labels.push(i.date);
        })

        counter('userTotalCount', data.data.userTotalCount);
        counter('examTotalCount', data.data.examTotalCount);
        counter('questionTotalCount', data.data.questionTotalCount);
        counter('resultTotalCount', data.data.resultTotalCount);

        const newUsersCountByDate = new ApexCharts(existId('newUsersCountByDate'), dashboardData.newUsersCountByDate);
        newUsersCountByDate.render();

    });

    const examResultSubmitNumber = new ApexCharts(existId('examResultSubmitNumber'), dashboardData.examResultSubmitNumber);
    examResultSubmitNumber.render();

    const test1chart = new ApexCharts(existId('test1chart'), dashboardData.test1chart);
    test1chart.render();

    const test2chart = new ApexCharts(existId('test2chart'), dashboardData.test2chart);
    test2chart.render();


}

$(document).ready(function () {
    loadDashboard();
});