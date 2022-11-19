let emptyData = {};
let index = 1;
function getMainDataList() {
    let dataList = "";
    httpUtil.defaultRequest('/api/admin/get-main-category','post', emptyData, function(data) {
        for(let i of data.data){
            dataList += `<option data-value = "${i.idx}" value ="${i.mainCategoryName}" />`
        }
        $("#data_list").html(dataList);
    })
}
function getSubDataList() {
      let main_ctg_name = $("#main_ctg_name").val();
      let mainIdx = $("#data_list [value='" + main_ctg_name + "']").data("value");
      if(mainIdx == "" || mainIdx == null){
        $("#data_list2").html("");
        $("#data_list3").html("");
        $("#sub_ctg_name").val("");
        for(let i=1; i<=index; i++){
            $(".subject_"+i).val("");
        }
      }else{
            let subDataList = "";
            let data = mainIdx;
            httpUtil.defaultRequest('/api/admin/get-sub-category','post', data, function(data) {
                for(let i of data.data){
                  subDataList += `<option data-value='${i.idx}' value='${i.subCategoryName}' />`
                }
            $("#data_list2").html(subDataList);
          })
      }
}

function getSubjectDataList() {
      let sub_ctg_name = $("#sub_ctg_name").val();
      let subIdx = $("#data_list2 [value='" + sub_ctg_name + "']").data("value");
      if(subIdx == "" || subIdx == null){
        for(let i=1; i<=index; i++){
            $(".subject_"+i).val("");
        }
        $("#data_list3").html("");
      }else{
            let data = subIdx;
            httpUtil.defaultRequest('/api/admin/get-subject-category','post', data, function(data) {
                let subjectDataList = "";
                for(let i of data.data){
                subjectDataList += "<option data-value='" + i.idx + "' value='" + i.subjectName + "' />"
                }
                $("#data_list3").html(subjectDataList);
            })

      }
}

function changeOptionCnt() {
      let count = $("#option_count").val()
      if(count == "5"){
        for(let i = 1; i<=index; i++){
          $("#No" + i).append(
            `<div class='col-md-12' id='choice'>
              <div id='choice_index'>5.</div>
              <input type='radio' name='correct_check_${i}' id='correct_check' value='5'>
              <textarea id='question' class='choice_content_5 form-control' name='' rows='2' placeholder='보기' required></textarea>
              </div>`
          )
        }
      }
      if(count == "4"){
        for(let i = 1; i<=index; i++){
          $("#No" + i).children("#choice").last().remove();
        }
      }
}

function plusIcon() {
                index++;
                let html_first = "";
                let html_middle = "";
                let html_end = "";
                let option_count = $("#option_count").val()
                html_first += `<div class='question_data_${index} row gy-4' id='add'>
                      <div class='col-md-12'>
                        <div class='accordion-item'>
                          <input id='question_subject' type='text' class='question${index} form-control' name='subject' placeholder='No ${index}.' required>
                          <button id='hide_button' class='accordion-button collapsed' type='button' data-bs-toggle='collapse' data-bs-target='#No${index}' />
                        </div>
                      </div>
                      <div id='No${index}' class='accordion-collapse collapse' data-bs-parent='#faqlist'>
                        <div class='filebox'>
                          <input class='upload-name form-control' id='upload_name_${index}' readonly placeholder='첨부파일'>
                          <label for='file${index}'>파일찾기</label>
                          <input type='file' id='file${index}'>
                          <div class="" id="select_subject">
                              <input type="text" class="subject_${index} form-control" id="subject_name" list="data_list3" placeholder="과목 선택">
                              <datalist id="data_list3" class="subject_datalist_${index} col-md-6">
                              </datalist>
                          </div>
                          </div>
                          <div class='img_wrap'>
                            <img class='mt-5' id='img${index}' />
                            </div>`
                for(let i=1; i<=option_count;i++){
              html_middle +=    `<div class='col-md-12' id='choice'>
                                    <div id='choice_index'>${i}.</div>
                                    <input type='radio' name='correct_check_${index}' id='correct_check' value='${i}'>
                                    <textarea id='question' class='choice_content_${i} form-control' name='' rows='2' placeholder='보기' required></textarea>
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
        let questionInfo = {};
        let mainCategoryName = existIdValue("main_ctg_name");
        let mainCategoryIdx = $("#data_list [value='" + mainCategoryName + "']").data("value");
        if(isEmptyStr(mainCategoryIdx)){
           warningMessageToast("메인 카테고리를 선택해야 합니다.");
           return false;
        }
        let subCategoryName = existIdValue("sub_ctg_name");
        let subCategoryIdx = $("#data_list2 [value='" + subCategoryName + "']").data("value");
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
        let questionCnt = index;

        questionInfo.mainCategoryIdx = mainCategoryIdx;
        questionInfo.subCategoryIdx = subCategoryIdx;
        questionInfo.examName = examName;
        questionInfo.optionCnt = optionCnt;
        questionInfo.questionCnt = questionCnt;

        let questions = [];
        for(let i = 1; i <= index; i++){
            let question = {};
            question.questionNumber = i;
            question.questionName = $(".question"+i).val();

            let subjectName = $(".subject_"+i).val();
            let subjectIdx = $("#data_list3 [value='" + subjectName + "']").data("value");
            question.SubjectIdx = subjectIdx;

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

            question.multiMediaIdx = null;
            let options = [];
            let no1 = $("#No"+i).find(".choice_content_1").val();
            let no2 = $("#No"+i).find(".choice_content_2").val();
            let no3 = $("#No"+i).find(".choice_content_3").val();
            let no4 = $("#No"+i).find(".choice_content_4").val();
            options.push(no1, no2, no3, no4)
            if(optionCnt == 5){
                let no5 = $("#No"+i).find(".choice_content_5").val();
                options.push(no5);
            }
            question.options = options;
            questions.push(question);
        }
        data.questionInfo = questionInfo;
        data.questions = questions;
        console.log(data);
}


