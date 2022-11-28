let choiceInfo = {}

/**
 * 자격증 조회
 */
function selectCertificate() {
    const data = {};

    httpUtil.defaultRequest('/api/certificate', 'POST', data, (data) => {
        let certificates = '';
        $.each(data.data, function () {
            certificates += `
            <option value='${this.certificateName}' onclick="selectSubjectAndExam(${this.mainIdx}, ${this.subIdx})">${this.certificateName}</option>
            `;
        });
        existId('certificateList').innerHTML = certificates;
    });
}

/**
 * 과목 및 회차 조회
 * @param mainIdx
 * @param subIdx
 */
function selectSubjectAndExam(mainIdx, subIdx) {
    choiceInfo.mainCtgIdx = mainIdx;
    choiceInfo.subCtgIdx = subIdx;
    const data = {};
    let url = `/api/subject/${subIdx}`;
    httpUtil.defaultRequest(url, 'POST', data, (data) => {
        let subjects = '';
        $.each(data.data, function () {
            subjects += `
                <input type="checkbox" name="subject" value="${this.subjectIdx}" checked>&nbsp;${this.subjectName}<br>
            `;
        });
        existId('d-subjects').innerHTML = subjects;
    });

    url = `/api/exam/${subIdx}`;
    httpUtil.defaultRequest(url, 'post', data, (data) => {
        let exams = '';
        $.each(data.data, function () {
            exams += `
                <input type="checkbox" name="exam" value="${this.examIdx}">&nbsp;${this.examName}<br>
            `;
        });
        existId('d-exams').innerHTML = exams;
    });
}

/**
 * 선택 유무 확인, 선택 조건들 저장
 * @param name
 */
function checkTerm(name) {
    // 자격증 선택유무 판별하는 코드
    let checkCertificate = existId('selector').value;
    // 과목 선택유무 판별코드
    let subjectIdxArray = [];
    let checkSubject = existName('subject');
    let subjectChecked = 0;
    for (let i = 0; i < checkSubject.length; i++) {
        if (checkSubject[i].checked) {
            subjectChecked++;
            // 선택되어 있는 과목 value 를 가져와 subjectIdxArray 배열에 추가
            subjectIdxArray.push(checkSubject[i].value);
        }
    }
    // 회차 선택유무 판별코드
    let examIdxArray = [];
    let checkExam = existName('exam');
    let examChecked = 0;
    for (let i = 0; i < checkExam.length; i++) {
        if (checkExam[i].checked) {
            examChecked++;
            // 선택되어 있는 회차 value 를 가져와 examIdxArray 배열에 추가
            examIdxArray.push(checkExam[i].value);
        }
    }
    // 각 배열에 추가된 과목, 회차를 json 형태인 choiceInfo 에 추가
    choiceInfo.subjectIdxArray = subjectIdxArray;
    choiceInfo.examIdxArray = examIdxArray;
    console.log(choiceInfo);
    window.name = 'examChoice';
    if (isEmptyStr(checkCertificate)) {
        warningMessageToast('자격증을 선택하여 주십시오.');
    } else if (subjectChecked === 0) {
        warningMessageToast('과목을 하나이상 선택하여 주십시오.');
    } else if (examChecked === 0) {
        warningMessageToast('회차를 하나이상 선택하여 주십시오.');
    } else {
        if (name === 'all') {
            let examAll = window.open('/exam-all', 'exam-all', 'menubar=0, resizable=0, width=1200, height=1000');
            examAll.opener.choiceInfo = choiceInfo;
        } else if (name === 'singly') {
            let examSingle = window.open('/exam-single', 'exam-single', 'menubar=0, resizable=0, width=1200, height=1000');
            examSingle.opener.choiceInfo = choiceInfo;
        }
    }
}