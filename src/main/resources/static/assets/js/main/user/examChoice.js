/**
 * 자격증 조회
 */
function selectCertificate() {
    const data = {};

    httpUtil.defaultRequest('/api/certificate', 'POST', data, function (data) {
        let certificates = '';
        $.each(data.data, function () {
            certificates += `
            <option value='${this.questionName}' onclick="selectSubjectAndExam(${this.questionIdx})">${this.questionName}</option>
            `;
        });
        existId('certificateList').innerHTML = certificates;
    });
}

/**
 * 과목 및 회차 조회
 * @param questionIdx
 */
function selectSubjectAndExam(questionIdx) {
    const data = {};
    let url = `/api/subject/${questionIdx}`;
    httpUtil.defaultRequest(url, 'POST', data, function (data) {
        let subjects = '';
        $.each(data.data, function () {
            subjects += `
                    <input type="checkbox" name="subject" value="${this.subjectIdx}" checked>&nbsp;${this.subjectName}<br>
                `;
        });
        existId('d-subjects').innerHTML = subjects;
    });

    url = `/api/exam/${questionIdx}`;
    httpUtil.defaultRequest(url, 'post', data, function (data) {
        let exams = '';
        $.each(data.data, function () {
            exams += `
                    <input type="checkbox" name="exam" value="${this.examIdx}">&nbsp;${this.examName}<br>
                `;
        });
        existId('d-exams').innerHTML = exams;
    });
}

function checkTerm(name) {
    // 자격증을 선택했는지 검사할 변수 checkCertificate
    let checkCertificate = existId('selector').value;

    // 과목을 선택했는지 검사할 코드들
    let checkSubject = document.getElementsByName('subject');
    let subjectLength = checkSubject.length;
    let subjectChecked = 0;
    for (let i = 0; i < subjectLength; i++) {
        if (checkSubject[i].checked) {
            subjectChecked += 1;
        }
    }

    // 회차를 선택했는지 검사할 코드들
    let checkExam = document.getElementsByName('exam');
    let examLength = checkExam.length;
    let examChecked = 0;
    for (let i = 0; i < examLength; i++) {
        if (checkExam[i].checked) {
            examChecked += 1;
        }
    }

    if (isEmptyStr(checkCertificate)) {
        warningMessageToast('자격증을 선택하여 주십시오.');
    } else if (subjectChecked === 0) {
        warningMessageToast('과목을 하나이상 선택하여 주십시오.');
    } else if (examChecked === 0) {
        warningMessageToast('회차를 하나이상 선택하여 주십시오.');
    } else {
        if (name === 'all') {
            location.href = '/exam-all';
        } else if (name === 'singly') {
            location.href = '/exam-single';
        }
    }
}