let idxAndToken = {};
function result(idx, token) {
    idxAndToken.idx = idx;
    idxAndToken.token = token;
    httpUtil.defaultRequest(`/api/result?idx=${idx}&token=${token}`, 'GET', null, (data) => {
        console.log(data.data);
        // 과목별 점수
        let subjects = '';
        let num = 1;
        data.data.examSubjectResultList.forEach((examSubjectResultList) => {
            subjects += `
                <tr>
                    <td class="text-left fw-bold">${examSubjectResultList.subjectName}</th>
                    <td class="text-center">${examSubjectResultList.subjectCutOffScore}점</td>
                    <td class="text-center">${examSubjectResultList.subjectScore}점</td>
                    <td id="subject-passYn${num}" class="text-center subject-passYn">${examSubjectResultList.passYn}</td>
                </tr>
            `;
            num++;
        })
        // 평균 점수
        let examAllResult = data.data.examAllResultDto;
        subjects += `
            <tr class="table-primary">
                <td colspan="2" class="text-center fw-bold">평균점수</th>
                <td class="text-center fw-bold">${examAllResult.averageScore}점</td>
                <td id="all-passYn" class="text-center fw-bold">${examAllResult.passYn}</td>
            </tr>
        `;
        existId('result-subject').innerHTML = subjects;
        // 통과여부에 색 넣기
        if(examAllResult.passYn === "합격") {
            existId('all-passYn').style.color = "GREEN";
        } else {
            existId('all-passYn').style.color = "RED";
        }
        let num2 = 1;
        for(let i = 0; i < data.data.examSubjectResultList.length; i++) {
            if(data.data.examSubjectResultList[i].passYn === "합격") {
                existId(`subject-passYn${num2}`).style.color = "GREEN";
            } else {
                existId(`subject-passYn${num2}`).style.color = "RED";
            }
            num2++;
        }
    });
}

function goDetail() {
    location.href = `/result/detail?idx=${idxAndToken.idx}&token=${idxAndToken.token}`;
}