let count = 0;
let questionsNumber = 0;
let resultInfo = {};
let submitAnswerList = [];
function Terms() {
    // 자식창에서 가져온 JSON Object 정의
    let choiceInfo = opener.choiceInfo;
    console.log(choiceInfo);

    resultInfo.mainCategoryIdx = choiceInfo.mainCtgIdx;
    resultInfo.subCategoryIdx = choiceInfo.subCtgIdx;
    resultInfo.subjectCnt = choiceInfo.subjectIdxArray.length;

    // 페이지 입장 후 바로 출력되어야 하는 데이터들 불러오기
    let url = '/api/exam-question';
    httpUtil.loadingRequest(url, 'POST', choiceInfo, (data) => {
        console.log(data.data);
        // 시험제목
        let examSubject = '전체풀기 - ' + data.data.ongoingExamInfoDto.subCategoryName;
        existId('main-subject').innerHTML = examSubject;

        let questionsStart = '';
        let questionsEnd = '</ul></div></article>';
        let correctStart = '';
        let correctEnd = '</div>';
        // 시험문제
        data.data.questionList.forEach((questionList) => {
            questionsNumber = questionList.questionNumber;
            count++;
            let options = '';
            questionsStart += `
                <article class="entry">
                    <h2 class="exam-title">
                        ${questionList.questionNumber}.&nbsp;${questionList.questionName}
                    </h2>

                    <div class="entry-content">
            `;
            // 문제 이미지 출력
            if(questionList.fileId !== null) {
                questionsStart += `
                    <img src="/api/image/${questionList.fileId}" alt="문제이미지">
                `;
            }
            questionsStart += `
                    </div>

                    <div class="entry-meta">
                        <br>
                        <ul>
            `;
            // 시험선지
            questionList.optionsList.forEach((optionsList) => {
                options += `
                    <li class="d-flex align-items-center">
                        <input type="radio" name="option${count}" value="${optionsList.optionsNumber}" onclick="optionAndQuestion(this, ${count})"
                            class="correct-radio" id="option${count}-${optionsList.optionsNumber}">
                        <label for="option${count}-${optionsList.optionsNumber}">
                            ${optionsList.optionsNumber}.&nbsp;${optionsList.optionsContent}
                        </label>
                    </li>
                `;
            })
            questionsStart += options;
            questionsStart += questionsEnd;

            // 선지가 4개일 때, 5개일 때 각각 답안지의 정답수량 변화
            let corrects = '';
            let optionsCnt = data.data.ongoingExamInfoDto.optionsCnt;
            correctStart += `
                <div class="d-cell">
                    <span class="title fs-5 jb-700 mr-2">${questionList.questionNumber}.</span>
            `;

            for(let i = 1; i <= optionsCnt; i++) {
                correctStart += `
                    <li><input type="radio" name="question${questionList.questionNumber}" value="${i}" onclick="optionAndQuestion(this, ${questionList.questionNumber})"
                        class="correct-radio" id="question${questionList.questionNumber}-${i}">
                        <label for="question${questionList.questionNumber}-${i}">${i}</label></li>
                `;
            }
            correctStart += correctEnd;

            let correctInfo = {};
            correctInfo.questionIdx = questionList.questionIdx;
            correctInfo.subjectIdx = questionList.subjectIdx;
            correctInfo.questionNumber = questionList.questionNumber;
            correctInfo.inputAnswer = null;
            submitAnswerList.push(correctInfo);
        })
        resultInfo.submitAnswerList = submitAnswerList;
        existId('questions').innerHTML = questionsStart;
        existId('exam-correct').innerHTML = correctStart;
    });
}

function optionAndQuestion(item, number) {
    console.log(number);
    let name = item.name;
    let questionNumber = 'question' + number;
    let optionNumber = 'option' + number;
    let questionId = 'question' + number + '-' + item.value;
    let optionId = 'option' + number + '-' + item.value;
    if(name === questionNumber) {
        existId(optionId).click();
    } else {
        existId(questionId).click();
    }
}

function result() {
    let num = 1;
    for(let i = 0; i < resultInfo.submitAnswerList.length; i++) {
    let correct = '';
        let questionName = `question${num}`;
        try {
            resultInfo.submitAnswerList[i].inputAnswer = document.querySelector('input[name="' + questionName + '"]:checked').value;
        } catch(e) {
            resultInfo.submitAnswerList[i].inputAnswer = null;
        }
        num++;
    }
    console.log(resultInfo);
    let resultIdx = 10;
    let token = 13;
    opener.setResultInfo(resultIdx, token);
}