function Terms() {
    // 자식창에서 가져온 JSON Object 정의
    let choiceInfo = opener.choiceInfo;
    console.log(choiceInfo);

    let url = '/api/exam-question';
    httpUtil.defaultRequest(url, 'POST', choiceInfo, (data) => {

    });
}