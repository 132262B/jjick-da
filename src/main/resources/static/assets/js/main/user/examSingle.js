// 자식창에서 가져온 JSON Object 정의
let choiceInfo = opener.choiceInfo;
let resultInfo = {};
let submitAnswerList = [];
let count = 0;
let count2 = 0;
function Terms() {
    resultInfo.mainCategoryIdx = choiceInfo.mainCtgIdx;
    resultInfo.subCategoryIdx = choiceInfo.subCtgIdx;
    resultInfo.subjectCnt = choiceInfo.subjectIdxArray.length;
    let url = '/api/exam/start';
    httpUtil.loadingRequest(url, 'POST', choiceInfo, (data) => {
        console.log(data.data);
        // 위치 영역에 들어갈 정보
        let examSubject = '하나씩풀기 - ' + data.data.ongoingExamInfoDto.subCategoryName;
        existId('main-subject').innerHTML = examSubject;

        // submitAnswerList 에 들어갈 데이터
        data.data.questionList.forEach((questionList) => {
            let correctInfo = {};
            correctInfo.questionIdx = questionList.questionIdx;
            correctInfo.subjectIdx = questionList.subjectIdx;
            correctInfo.questionNumber = questionList.questionNumber;
            correctInfo.inputAnswer = null;
            submitAnswerList.push(correctInfo);
        })
        // 문항제목
        let questionList = data.data.questionList[count2];
        let title = `${questionList.questionNumber}.&nbsp;${questionList.questionName}`;
        existId('exam-title').innerHTML = title;
        // 문항이미지
        if(questionList.fileId !== null) {
            let img = `<img src="/api/image/${questionList.fileId}" alt="문제이미지">`;
            existId('entry-content').innerHTML = img;
        }
        // 문항선지
        count++;
        let options = '';
        questionList.optionsList.forEach((optionsList) => {
            options += `
                <li class="d-flex align-items-center">
                    <input type="radio" name="option${count}" value="${optionsList.optionsNumber}" onclick="putAnswer(this)"
                        class="correct-radio" id="option${count}-${optionsList.optionsNumber}">
                    <label for="option1-${optionsList.optionsNumber}">
                        ${optionsList.optionsNumber}.&nbsp;${optionsList.optionsContent}
                    </label>
                </li>
            `;
        })
        existId("exam-option").innerHTML = options;
        resultInfo.submitAnswerList = submitAnswerList;
        console.log(resultInfo);
    });
}

let nullCheck = 0;
function putAnswer(item) {
    try {
        resultInfo.submitAnswerList[count2].inputAnswer = document.querySelector(`input[name=${item.name}]:checked`).value;
    } catch(e) {
        resultInfo.submitAnswerList[count2].inputAnswer = null;
        nullCheck++;
    }
}

let num = 1;
function nextQuestion() {
    if(nullCheck > 0) {
        let isConfirm = confirm("답을 선택하지않으셨습니다.\n넘어가시겠습니까?");
        if(isConfirm) {
            count++;
        }
    }
    num++;
}
