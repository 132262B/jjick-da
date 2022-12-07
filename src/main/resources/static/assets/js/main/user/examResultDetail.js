function detail(idx, token) {
    httpUtil.defaultRequest(`/api/result/detail?idx=${idx}&token=${token}`, 'GET', null, (data) => {
        console.log(data.data);
        let resultQuestion = '';
        let count = 1;
        data.data.forEach((data) => {
            // 시험문항
            resultQuestion += `
                <article class="entry">
                    <h2 class="exam-title">
                        (<span id="answerColor${count}">${data.answerYn}</span>) ${data.questionNumber}.&nbsp;${data.questionName}
                    </h2>

                    <div class="entry-content">
            `;
            // 문제 이미지
            if(data.fileId !== null) {
                resultQuestion += `
                    <img src="/api/image/${data.fileId}" alt="문제이미지">
                `;
            }
            resultQuestion += `
                    </div>

                    <div class="entry-meta">
                        <br>
                        <ul>
            `;
            // 문항선지
            let options = '';
            let count2 = 1;
            let inputAnswer = data.inputAnswer;
            data.optionsList.forEach((optionsList) => {
                if(inputAnswer === count2) {
                    options += `
                        <li class="d-flex align-items-center">
                            <input type="radio" name="option${count}" value="${optionsList.optionsNumber}"
                                class="correct-radio" id="option${count}-${optionsList.optionsNumber}" checked disabled>
                            <label id="label${count}-${optionsList.optionsNumber}" for="option${count}-${optionsList.optionsNumber}">
                                ${optionsList.optionsNumber}.&nbsp;${optionsList.optionsContent}
                            </label>
                        </li>
                    `;
                } else {
                    options += `
                        <li class="d-flex align-items-center">
                            <input type="radio" name="option${count}" value="${optionsList.optionsNumber}"
                                class="correct-radio" id="option${count}-${optionsList.optionsNumber}" disabled>
                            <label id="label${count}-${optionsList.optionsNumber}" for="option${count}-${optionsList.optionsNumber}">
                                ${optionsList.optionsNumber}.&nbsp;${optionsList.optionsContent}
                            </label>
                        </li>
                        </li>
                    `;
                }
                count2++;
            })
            resultQuestion += options;
            resultQuestion += `</ul><br><div class="detail-correct fw-bold">정답 : ${data.answerNumber}, 선택한 정답 : ${data.inputAnswer}</div></div></article>`;
            count++;
        })
        existId("result-question").innerHTML = resultQuestion;

        let i = 1;
        data.data.forEach((data) => {
            existId(`label${i}-${data.answerNumber}`).style.color = "GREEN";
            if(data.inputAnswer === data.answerNumber) {
                existId(`answerColor${i}`).style.color = "GREEN";
            }else {
                existId(`answerColor${i}`).style.color = "RED";
            }
            i++;
        })
    });
}