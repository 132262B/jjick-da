// 자식창에서 가져온 JSON Object 정의
let choiceInfo = opener.choiceInfo;
let dataList = {};
let resultInfo = {};
let submitAnswerList = [];
let count = 0;
let count2 = 0;
function Terms() {
    resultInfo.mainCategoryIdx = choiceInfo.mainCtgIdx;
    resultInfo.subCategoryIdx = choiceInfo.subCtgIdx;
    resultInfo.subjectCnt = choiceInfo.subjectIdxArray.length;
    httpUtil.loadingRequest('/api/exam/start', 'POST', choiceInfo, (data) => {
        dataList = data.data;
        // 위치 영역에 들어갈 정보
        let examSubject = '하나씩풀기 - ' + dataList.ongoingExamInfoDto.subCategoryName;
        existId('main-subject').innerHTML = examSubject;

        // submitAnswerList 에 들어갈 데이터
        dataList.questionList.forEach((questionList) => {
            let correctInfo = {};
            correctInfo.questionIdx = questionList.questionIdx;
            correctInfo.subjectIdx = questionList.subjectIdx;
            correctInfo.questionNumber = questionList.questionNumber;
            correctInfo.inputAnswer = null;
            submitAnswerList.push(correctInfo);
        })
        // 문항제목
        let questionList = dataList.questionList[count2];
        console.log(questionList);
        let title = `${questionList.questionNumber}.&nbsp;${questionList.questionName}`;
        existId('exam-title').innerHTML = title;
        // 문항이미지
        let img = '';
        if(questionList.fileId !== null) {
            img = `<img src="/api/image/${questionList.fileId}" alt="문제이미지">`;
            existId('entry-content').innerHTML = img;
        } else {
            existId('entry-content').innerHTML = img;
        }
        // 문항선지
        count++;
        let options = '';
        let count3 = 1;
        questionList.optionsList.forEach((optionsList) => {
            options += `
                <li class="d-flex align-items-center">
                    <input type="radio" name="option${count}" value="${optionsList.optionsNumber}" onclick="putAnswer(this)"
                        class="correct-radio" id="option${count}-${optionsList.optionsNumber}">
                    <label for="option${count}-${optionsList.optionsNumber}">
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
    }
}

function nextQuestion() {
    if(count < resultInfo.submitAnswerList.length) {
        if(resultInfo.submitAnswerList[count2].inputAnswer === null) {
            let isConfirm = confirm("답을 선택하지않으셨습니다.\n다음으로 넘어가시겠습니까?");
            if(isConfirm) {
                count2++;
                // 문항제목
                let questionList = dataList.questionList[count2];
                let title = `${questionList.questionNumber}.&nbsp;${questionList.questionName}`;
                existId('exam-title').innerHTML = title;
                // 문항이미지
                let img = '';
                if(questionList.fileId !== null) {
                    img = `<img src="/api/image/${questionList.fileId}" alt="문제이미지">`;
                    existId('entry-content').innerHTML = img;
                } else {
                    existId('entry-content').innerHTML = img;
                }
                // 문항선지
                count++;
                let options = '';
                let count3 = 1;
                questionList.optionsList.forEach((optionsList) => {
                    if(resultInfo.submitAnswerList[count2].inputAnswer == count3) {
                        options += `
                            <li class="d-flex align-items-center">
                                <input type="radio" name="option${count}" value="${optionsList.optionsNumber}" onclick="putAnswer(this)"
                                    class="correct-radio" id="option${count}-${optionsList.optionsNumber}" checked>
                                <label for="option${count}-${optionsList.optionsNumber}">
                                    ${optionsList.optionsNumber}.&nbsp;${optionsList.optionsContent}
                                </label>
                            </li>
                        `;
                    } else {
                        options += `
                            <li class="d-flex align-items-center">
                                <input type="radio" name="option${count}" value="${optionsList.optionsNumber}" onclick="putAnswer(this)"
                                    class="correct-radio" id="option${count}-${optionsList.optionsNumber}">
                                <label for="option${count}-${optionsList.optionsNumber}">
                                    ${optionsList.optionsNumber}.&nbsp;${optionsList.optionsContent}
                                </label>
                            </li>
                        `;
                    }
                    count3++;
                })
                existId("exam-option").innerHTML = options;
            }
        } else {
            count2++;
            // 문항제목
            let questionList = dataList.questionList[count2];
            let title = `${questionList.questionNumber}.&nbsp;${questionList.questionName}`;
            existId('exam-title').innerHTML = title;
            // 문항이미지
            let img = '';
            if(questionList.fileId !== null) {
                img = `<img src="/api/image/${questionList.fileId}" alt="문제이미지">`;
                existId('entry-content').innerHTML = img;
            } else {
                existId('entry-content').innerHTML = img;
            }
            // 문항선지
            count++;
            let options = '';
            let count3 = 1;
            questionList.optionsList.forEach((optionsList) => {
                if(resultInfo.submitAnswerList[count2].inputAnswer == count3) {
                    options += `
                        <li class="d-flex align-items-center">
                            <input type="radio" name="option${count}" value="${optionsList.optionsNumber}" onclick="putAnswer(this)"
                                class="correct-radio" id="option${count}-${optionsList.optionsNumber}" checked>
                            <label for="option${count}-${optionsList.optionsNumber}">
                                ${optionsList.optionsNumber}.&nbsp;${optionsList.optionsContent}
                            </label>
                        </li>
                    `;
                } else {
                    options += `
                        <li class="d-flex align-items-center">
                            <input type="radio" name="option${count}" value="${optionsList.optionsNumber}" onclick="putAnswer(this)"
                                class="correct-radio" id="option${count}-${optionsList.optionsNumber}">
                            <label for="option${count}-${optionsList.optionsNumber}">
                                ${optionsList.optionsNumber}.&nbsp;${optionsList.optionsContent}
                            </label>
                        </li>
                    `;
                }
                count3++;
            })
            existId("exam-option").innerHTML = options;
        }
    } else {
        alert("다음 문제가 없습니다.");
    }
}

function beforeQuestion() {
    if(count > 1) {
        if(resultInfo.submitAnswerList[count2].inputAnswer === null) {
            let isConfirm = confirm("답을 선택하지않으셨습니다.\n이전으로 넘어가시겠습니까?");
            if(isConfirm) {
                count2--;
                // 문항제목
                let questionList = dataList.questionList[count2];
                let title = `${questionList.questionNumber}.&nbsp;${questionList.questionName}`;
                existId('exam-title').innerHTML = title;
                // 문항이미지
                let img = '';
                if(questionList.fileId !== null) {
                    img = `<img src="/api/image/${questionList.fileId}" alt="문제이미지">`;
                    existId('entry-content').innerHTML = img;
                } else {
                    existId('entry-content').innerHTML = img;
                }
                // 문항선지
                count--;
                let options = '';
                let count3 = 1;
                questionList.optionsList.forEach((optionsList) => {
                    if(resultInfo.submitAnswerList[count2].inputAnswer == count3) {
                        options += `
                            <li class="d-flex align-items-center">
                                <input type="radio" name="option${count}" value="${optionsList.optionsNumber}" onclick="putAnswer(this)"
                                    class="correct-radio" id="option${count}-${optionsList.optionsNumber}" checked>
                                <label for="option${count}-${optionsList.optionsNumber}">
                                    ${optionsList.optionsNumber}.&nbsp;${optionsList.optionsContent}
                                </label>
                            </li>
                        `;
                    } else {
                        options += `
                            <li class="d-flex align-items-center">
                                <input type="radio" name="option${count}" value="${optionsList.optionsNumber}" onclick="putAnswer(this)"
                                    class="correct-radio" id="option${count}-${optionsList.optionsNumber}">
                                <label for="option${count}-${optionsList.optionsNumber}">
                                    ${optionsList.optionsNumber}.&nbsp;${optionsList.optionsContent}
                                </label>
                            </li>
                        `;
                    }
                    count3++;
                })
                existId("exam-option").innerHTML = options;
            }
        } else {
            count2--;
            // 문항제목
            let questionList = dataList.questionList[count2];
            let title = `${questionList.questionNumber}.&nbsp;${questionList.questionName}`;
            existId('exam-title').innerHTML = title;
            // 문항이미지
            let img = '';
            if(questionList.fileId !== null) {
                img = `<img src="/api/image/${questionList.fileId}" alt="문제이미지">`;
                existId('entry-content').innerHTML = img;
            } else {
                existId('entry-content').innerHTML = img;
            }
            // 문항선지
            count--;
            let options = '';
            let count3 = 1;
            questionList.optionsList.forEach((optionsList) => {
                if(resultInfo.submitAnswerList[count2].inputAnswer == count3) {
                    options += `
                        <li class="d-flex align-items-center">
                            <input type="radio" name="option${count}" value="${optionsList.optionsNumber}" onclick="putAnswer(this)"
                                class="correct-radio" id="option${count}-${optionsList.optionsNumber}" checked>
                            <label for="option${count}-${optionsList.optionsNumber}">
                                ${optionsList.optionsNumber}.&nbsp;${optionsList.optionsContent}
                            </label>
                        </li>
                    `;
                } else {
                    options += `
                        <li class="d-flex align-items-center">
                            <input type="radio" name="option${count}" value="${optionsList.optionsNumber}" onclick="putAnswer(this)"
                                class="correct-radio" id="option${count}-${optionsList.optionsNumber}">
                            <label for="option${count}-${optionsList.optionsNumber}">
                                ${optionsList.optionsNumber}.&nbsp;${optionsList.optionsContent}
                            </label>
                        </li>
                    `;
                }
                count3++;
            })
            existId("exam-option").innerHTML = options;
        }
    } else {
        alert("이전 문제가 없습니다.");
    }
}

function result() {
    let nullCheck = 0;
    for(let i=0; i < resultInfo.submitAnswerList.length; i++) {
        if(resultInfo.submitAnswerList[i].inputAnswer === null) {
            nullCheck++;
        }
    }
    if(nullCheck > 0) {
        let isConfirm = confirm(messageUtil.MESSAGE_EXAM_SUBMIT_CONFIRM_ALERT);
        if(isConfirm) {
            httpUtil.defaultRequest('/api/exam/submit', 'POST', resultInfo, (data) => {
                opener.setResultInfo(data.data.resultIdx, data.data.token);
                window.close();
            });
        }
    } else {
        httpUtil.defaultRequest('/api/exam/submit', 'POST', resultInfo, (data) => {
            opener.setResultInfo(data.data.resultIdx, data.data.token);
            window.close();
        });
    }
}
