let emptyData = {};
let index = 1;
let totalScore = 0;

var form = new FormData();

console.log(typeof form)

function getMainDataList() {
    let dataList = `<option disabled selected>메인 카테고리 선택</option>`;
    httpUtil.defaultRequest('/api/admin/get-main-category','post', emptyData, (data) => {
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
      $("#th_"+i).val("");
      $("#leftScore_"+i).html("");
      }
      let mainIdx = existIdValue("main_ctg_name");
      if(mainIdx == "" || mainIdx == null){
      }else{
            let subDataList = `<option disabled selected>서브 카테고리 선택</option>`;
            let data = mainIdx;
            httpUtil.defaultRequest('/api/admin/get-sub-category','post', data, (data) => {
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
          $("#th_"+i).val("");
          $("#leftScore_"+i).html("");
      }
      let subIdx = existIdValue("sub_ctg_name");
      if(subIdx == "" || subIdx == null){
      }else{
            let data = subIdx;
            httpUtil.defaultRequest('/api/admin/get-subject-category','post', data, (data) => {
                let subjectDataList = "";
                totalScore = 0;
                for(let i of data.data){
                    totalScore += 100;
                    subjectDataList += `<option data-value='${i.idx}' value='${i.subjectName}' />`
                }
                $("#data_list3").html(subjectDataList);
            })
      }
}
function changeSubject(target) {
    $("#th_"+target).val("")
    changeScore(target);
}
function changeScore(target) {
    let subjectName = $("#subject_"+target).val();
    let subjectIdx = $("#data_list3 [value='" + subjectName + "']").data("value");
    let plusScore = 100;

    for(let i = 1; i <= index; i++){
        if($("#subject_"+i).val() == subjectName){
            plusScore -= $("#th_"+i).val();
        }
    }
    for(let i = 1; i <= index; i++){
        if($("#subject_"+i).val() == subjectName){
            $("#leftScore_"+i).html(plusScore);
        }
    }
    if(isEmptyStr(subjectIdx)){
        $("#leftScore_"+target).html("");
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
                            <div id='hidden_html${index}'></div>
                            <input type="text" onchange="changeSubject(${index});" class="subject_name form-control" id="subject_${index}" list="data_list3" placeholder="과목 선택">
                            <datalist id="data_list3" class="subject_datalist_${index} col-md-6">
                            </datalist>
                            <input id="question${index}" type="text" class="question_subject form-control" name="subject" placeholder="No ${index}." required>
                            <button class="hide_button accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#No${index}" />
                        </div>
                    </div>
                      <div id='No${index}' class='accordion-collapse collapse' data-bs-parent='#faqlist'>
                        <div class='filebox'>
                          <input class='upload-name form-control' id='upload_name_${index}' readonly placeholder='첨부파일'>
                          <label for='file${index}'>파일찾기</label>
                          <input type='file' id='file${index}' onchange='uploadFile(this);'>
                          <input type="number" id="th_${index}" class="score form-control" placeholder="점수" onkeyup="onchangeNum(this); positiveNumber(this); changeScore(${index})">
                          <div class="score remnantScore form-control"><span>남은 점수 : </span><span id="leftScore_${index}"></span></div>
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
      $("#minus_icon").prev().prev(".add:not(:first-child)").remove();
      index--;
      if(index < 1){
        index = 1;
      }
}
function uploadFile(target) {
    let file = document.getElementById("file1");
    let file_id_name = $(target).attr('id');
    let file_index = file_id_name.substr(4,4);
    let fileName = $("#"+file_id_name).val();
    let hidden = "";
    if(file.files.length != 1) {
        $("#upload_name_"+file_index).val("");
        $("#hidden_html"+file_index).html(hidden);
        return false;
    }
    if(!extensionValidation(target)){
        $("#upload_name_"+file_index).val("");
        $("#hidden_html"+file_index).html(hidden);
        return false;
    }
    $("#upload_name_"+file_index).val(fileName);

    var fileData = new FormData();
    fileData.append("file", $("#file"+file_index)[0].files[0]);

    httpUtil.uploadRequest('/api/upload/file','post', fileData, (data) => {
        successMessageToast("1건의 파일이 등록 되었습니다.");
        hidden = `<input type='hidden' id='multimedia${file_index}' value='${data.data.multiMediaIdx}'>`
        $("#hidden_html"+file_index).html(hidden);
    })
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
        let examCutOffScore = existIdValue("examCutOffScore");
        if(isEmptyStr(examCutOffScore) || examCutOffScore < 1 || examCutOffScore > 100){
            warningMessageToast("과목 합격 기준을 입력해야 합니다.")
            return false;
        }
        let score = 0;
        for(let i = 1; i <= index; i++){
            let inputScore = $("#th_"+i).val();
            score = parseInt(inputScore) + parseInt(score);
            if($("#leftScore_"+i).text()< 0){
                warningMessageToast("남은 점수가 음수일 수 없습니다.");
                return false;
            }
        }
        if(score != totalScore){
            warningMessageToast("총 점수("+totalScore+")점을 맞춰야 합니다.");
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
        examInfo.examCutOffScore = examCutOffScore;
        let questions = [];
        for(let i = 1; i <= index; i++){
            let question = {};
            question.questionNumber = i;
            question.questionName = $("#question"+i).val();
            if(isEmptyStr(question.questionName)){
                warningMessageToast(i+"번 문항의 제목을 입력해야 합니다.");
                return false;
            }

            let subjectName = $("#subject_"+i).val();
            let subjectIdx = $("#data_list3 [value='" + subjectName + "']").data("value");
            question.subjectIdx = subjectIdx;
            if(isEmptyStr(question.subjectIdx)){
                warningMessageToast(i+"번 문항의 과목을 선택해야 합니다.");
                return false;
            }

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
            question.score = $("#th_"+i).val();
            if(isEmptyStr(question.score)){
                warningMessageToast(i+"번 문항의 점수를 입력해야 합니다.");
                return false;
            }
            question.score = parseInt(question.score);
            question.answerNumber = parseInt(question.answerNumber);
            if($("#multimedia"+i).length < 1){
                question.multiMediaIdx = null;
            }else{
                question.multiMediaIdx = $("#multimedia"+i).val();
            }


            let options = [];
            let option = {};
            let no1 = $("#No"+i).find(".choice_content_1").val();
            if(isEmptyStr(no1)){
                warningMessageToast(i+"번 문항의 1번 선지를 입력해야 합니다.");
                return false;
            }
            let no2 = $("#No"+i).find(".choice_content_2").val();
            if(isEmptyStr(no2)){
                warningMessageToast(i+"번 문항의 2번 선지를 입력해야 합니다.");
                return false;
            }
            let no3 = $("#No"+i).find(".choice_content_3").val();
            if(isEmptyStr(no3)){
                 warningMessageToast(i+"번 문항의 3번 선지를 입력해야 합니다.");
                 return false;
            }
            let no4 = $("#No"+i).find(".choice_content_4").val();
            if(isEmptyStr(no4)){
              warningMessageToast(i+"번 문항의 4번 선지를 입력해야 합니다.");
              return false;
            }
            if(optionCnt == 5){
                let no5 = $("#No"+i).find(".choice_content_5").val();
                if(isEmptyStr(no5)){
                    warningMessageToast(i+"번 문항의 5번 선지를 입력해야 합니다.");
                    return false;
                }
            }
            for(let k = 1;k <= optionCnt; k++){
                option = {};
                option.optionNumber = k;
                option.optionContent = $("#No"+i).find(".choice_content_"+k).val();
                options.push(option);
            }
            question.options = options;
            questions.push(question);
        }
        data.examInfo = examInfo;
        data.questions = questions;
    console.log(JSON.stringify(data));
    httpUtil.defaultRequest('/api/admin/add-exam','post', data, (data) => {
        successMessageToast(data.data.message);
    })

}
