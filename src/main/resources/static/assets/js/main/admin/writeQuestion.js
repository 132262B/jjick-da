let emptyData = {};
let index = 1;

let subjectScore = {};
function getMainDataList() {
    subjectScore = {};
    let dataList = `<option disabled selected>메인 카테고리 선택</option>`;
    httpUtil.defaultRequest('/api/admin/get-main-category','post', emptyData, function(data) {
        for(let i of data.data){
            dataList += `<option value = "${i.idx}"/>${i.mainCategoryName}</option>`
        }
        $("#main_ctg_name").html(dataList);
    })
}
function getSubDataList() {
      $("#data_list2").html("");
      $("#data_list3").html("");
      $("#sub_ctg_name").val("");
      for(let i=1; i<=index; i++){
      $("#subject_"+i).val("");
      }
      subjectScore = {};
      let mainIdx = existIdValue("main_ctg_name");
      if(mainIdx == "" || mainIdx == null){
      }else{
            let subDataList = `<option disabled selected>서브 카테고리 선택</option>`;
            let data = mainIdx;
            httpUtil.defaultRequest('/api/admin/get-sub-category','post', data, function(data) {
                for(let i of data.data){
                  subDataList += `<option value='${i.idx}'>${i.subCategoryName}</option>`
                }
            $("#sub_ctg_name").html(subDataList);
          })
      }
}

function getSubjectDataList() {
      $("#data_list3").html("");
      for(let i=1; i<=index; i++){
          $("#subject_"+i).val("");
      }
      subjectScore = {};
      let subIdx = existIdValue("sub_ctg_name");
      if(subIdx == "" || subIdx == null){
      }else{
            let data = subIdx;
            httpUtil.defaultRequest('/api/admin/get-subject-category','post', data, function(data) {
                for(let i of data.data){
                    subjectScore += `{subjectScore${i.idx} : 100}`;
                }

                let subjectDataList = "";
                for(let i of data.data){
                    subjectDataList += `<option data-value='${i.idx}' value='${i.subjectName}' />`
                }
                $("#data_list3").html(subjectDataList);
            })
      }
}

function changeOptionCnt() {
      let count = $("#option_count").val();
      if(count == "5"){
        for(let i = 1; i<=index; i++){
          $("#No" + i).append(
            `<div class='col-md-12' id='choice'>
              <div id='choice_index'>5.</div>
              <input type='radio' name='correct_check_${i}' class='correct_check' value='5'>
              <textarea id='' class='question choice_content_5 form-control' name='' rows='2' placeholder='보기' required></textarea>
              </div>`
          )
        }
      }
      if(count == "4"){
        for(let i = 1; i<=index; i++){
          $("#No" + i).children(".choice").last().remove();
        }
      }
}

function plusIcon() {
                index++;
                let html_first = "";
                let html_middle = "";
                let html_end = "";
                let option_count = $("#option_count").val()
                html_first += `<div class='add question_data_${index} row gy-4'>
                    <div class="col-md-12">
                        <div class="info_wrap accordion-item">
                            <input type="text" class="subject_name form-control" id="subject_${index}" list="data_list3" placeholder="과목 선택">
                            <datalist id="data_list3" class="subject_datalist_1 col-md-6">
                            </datalist>
                            <input id="question${index}" type="text" class="question_subject form-control" name="subject" placeholder="No ${index}." required>
                            <button class="hide_button accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#No${index}" />
                        </div>
                    </div>
                      <div id='No${index}' class='accordion-collapse collapse' data-bs-parent='#faqlist'>
                        <div class='filebox'>
                          <input class='upload-name form-control' id='upload_name_${index}' readonly placeholder='첨부파일'>
                          <label for='file${index}'>파일찾기</label>
                          <input type='file' id='file${index}'>
                          </div>`
                for(let i=1; i<=option_count;i++){
              html_middle +=    `<div class="choice col-md-12" id="">
                                      <div class="choice_index">${i}.</div>
                                      <input type="radio" name="correct_check_${index}" class="correct_check" value="${i}">
                                      <textarea id="" class="question choice_content_${i} form-control" name="" rows="2" placeholder="보기" required></textarea>
                                  </div>`
            }
            html_end ="</div></div>"

            let html = html_first+html_middle+html_end;
            $("#plus_icon").before(html);
}

function minusIcon() {
      $("#minus_icon").prev().prev("#add:not(:first-child)").remove();
      index--;
      if(index < 1){
        index = 1;
      }
}

function registQuestion() {
        let data = {};
        let examInfo = {};
        let mainCategoryIdx = existIdValue("main_ctg_name");
        mainCategoryIdx= parseInt(mainCategoryIdx);
        if(isEmptyStr(mainCategoryIdx)){
           warningMessageToast("메인 카테고리를 선택해야 합니다.");
           return false;
        }
        let subCategoryIdx = existIdValue("sub_ctg_name");
        subCategoryIdx= parseInt(subCategoryIdx);
        if(isEmptyStr(subCategoryIdx)){
           warningMessageToast("서브 카테고리를 선택해야 합니다.");
           return false;
        }
        let examName = existIdValue("examName");
        if(isEmptyStr(examName)){
           warningMessageToast("문항 제목을 입력해야 합니다.");
           return false;
        }
        let optionCnt = existIdValue("option_count");
        optionCnt = parseInt(optionCnt);
        let questionCnt = index;
        questionCnt = parseInt(questionCnt);

        examInfo.mainCategoryIdx = mainCategoryIdx;
        examInfo.subCategoryIdx = subCategoryIdx;
        examInfo.examName = examName;
        examInfo.optionCnt = optionCnt;
        examInfo.questionCnt = questionCnt;

        let questions = [];
        for(let i = 1; i <= index; i++){
            let question = {};
            question.questionNumber = i;
            question.questionName = $("#question"+i).val();

            let subjectName = $("#subject_"+i).val();
            let subjectIdx = $("#data_list3 [value='" + subjectName + "']").data("value");
            question.subjectIdx = subjectIdx;

            const getAnswerNumber = document.getElementsByName("correct_check_"+i);
            getAnswerNumber.forEach((answer) => {
                if(answer.checked) {
                    question.answerNumber = answer.value;
                }
            })
            if(isEmptyStr(question.answerNumber)){
                warningMessageToast(i+"번 문항의 정답을 체크해야 합니다.");
                return false;
            }
            question.answerNumber = parseInt(question.answerNumber);

           question.multiMediaIdx = null;
            let options = [];
            let option = {};
            let no1 = $("#No"+i).find(".choice_content_1").val();
            let no2 = $("#No"+i).find(".choice_content_2").val();
            let no3 = $("#No"+i).find(".choice_content_3").val();
            let no4 = $("#No"+i).find(".choice_content_4").val();
            option.option1 = no1;
            option.option2 = no2;
            option.option3 = no3;
            option.option4 = no4;
            if(optionCnt == 5){
                let no5 = $("#No"+i).find(".choice_content_5").val();
            option.option5 = no5;
            }
            options.push(option);
            question.options = options;
            questions.push(question);
        }
        data.examInfo = examInfo;
        data.questions = questions;
        console.log(data);
}
