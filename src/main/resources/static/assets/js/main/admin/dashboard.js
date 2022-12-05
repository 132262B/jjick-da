let dashboardData = {
    "twoWeeksNewUsersCountAndDate": {
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
    "twoWeeksSubmitResultCountAndDate": {
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
            text: '시험 제출수',
            offsetX: 10,
            style: {
                fontSize: '24px',
                cssClass: 'apexcharts-yaxis-title'
            }
        },
    },
    "topCountByExamFiveList": {
        series: [],
        chart: {
            width: 325,
            type: 'pie',
        },
        labels: [],
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

        // 2주 신규 사용자 통계
        data.data.twoWeeksNewUsersCountAndDate.forEach((i) => {
            dashboardData.twoWeeksNewUsersCountAndDate.series[0].data.push(i.count);
            dashboardData.twoWeeksNewUsersCountAndDate.labels.push(i.date);
        })

        // 2주 결과 제출수 통계
        data.data.twoWeeksSubmitResultCountAndDate.forEach((i) => {
            dashboardData.twoWeeksSubmitResultCountAndDate.series[0].data.push(i.count);
            dashboardData.twoWeeksSubmitResultCountAndDate.labels.push(i.date);
        })

        // 2주 결과제출 상위 5개 통계
        data.data.topCountByExamFiveList.forEach((i) => {
            dashboardData.topCountByExamFiveList.series.push(i.count);
            dashboardData.topCountByExamFiveList.labels.push(i.data);
        })

        counter('userTotalCount', data.data.userTotalCount);
        counter('examTotalCount', data.data.examTotalCount);
        counter('questionTotalCount', data.data.questionTotalCount);
        counter('resultTotalCount', data.data.resultTotalCount);

        const twoWeeksNewUsersCountAndDate = new ApexCharts(existId('twoWeeksNewUsersCountAndDate'), dashboardData.twoWeeksNewUsersCountAndDate);
        twoWeeksNewUsersCountAndDate.render();

        const twoWeeksSubmitResultCountAndDate = new ApexCharts(existId('twoWeeksSubmitResultCountAndDate'), dashboardData.twoWeeksSubmitResultCountAndDate);
        twoWeeksSubmitResultCountAndDate.render();

        const topCountByExamFiveList = new ApexCharts(existId('topCountByExamFiveList'), dashboardData.topCountByExamFiveList);
        topCountByExamFiveList.render();

    });

    const test2chart = new ApexCharts(existId('test2chart'), dashboardData.test2chart);
    test2chart.render();


}

$(document).ready(function () {
    loadDashboard();
});