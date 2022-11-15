function signUp() {
    resetInvalid();

    const email = existId('email');
    const password = existId('password');
    const passwordCheck = existId('passwordCheck');
    const name = existId('name');

    // 이메일 형태가 아닌경우
    if (!validator.isEmail(email.value)) {
        email.classList.add('is-invalid');
        existId('invalidEmail').innerText = messageUtil.MESSAGE_EMAIL_CHECK;
        return false;
    }

    // 비밀번호와 비밀번호확인이 일치하지 않는 경우
    if (password.value !== passwordCheck.value) {
        password.classList.add('is-invalid');
        passwordCheck.classList.add('is-invalid');
        existId('invalidPassword').innerText = '';
        existId('invalidPasswordCheck').innerText = messageUtil.MESSAGE_PASSWORD_NOT_EQUALS;
        return false;
    }

    // 비밀번호 체크
    if (!validator.isStrongPassword(password.value, {
        minLength: 7, minLowercase: 1, minUppercase: 1, minNumbers: 1
    })) {
        password.classList.add('is-invalid');
        existId('invalidPassword').innerText = messageUtil.MESSAGE_PASSWORD_CHECK;
        return false;
    }

    if (!validator.isStrongPassword(passwordCheck.value, {
        minLength: 7, minLowercase: 1, minUppercase: 1, minNumbers: 1
    })) {
        passwordCheck.classList.add('is-invalid');
        existId('invalidPasswordCheck').innerText = messageUtil.MESSAGE_PASSWORD_CHECK;
        return false;
    }

    // 닉네임 길이 체크
    if (!validator.isLength(name.value, {
        min: 2, max: 20
    })) {
        name.classList.add('is-invalid');
        existId('invalidName').innerText = messageUtil.MESSAGE_NAME_LENGTH_CHECK;
        return false;
    }

    const data = {};
    data.email = email.value;
    data.password = sha256(password.value);
    data.name = name.value;

    httpUtil.defaultRequest('/api/user/sign-up', 'post', data,
        function (data) {
            if(data.data.success) {
                location.href = '/welcome'
            } else {
                email.classList.add('is-invalid');
                existId('invalidEmail').innerText = data.data.message;
            }
        });

}

/**
 * 아이디 중복체크
 */
function emailCheck() {
    resetEmail();

    const email = existId('email');
    // 이메일 형태가 아닌경우
    if (!validator.isEmail(email.value)) {
        email.classList.add('is-invalid');
        existId('invalidEmail').innerText = messageUtil.MESSAGE_EMAIL_CHECK;
        return false;
    }

    let data = {};
    data.email = email.value;

    console.log(data);
    httpUtil.defaultRequest('/api/user/email-duplication', 'post', data,
        function (data) {

            if (!data.data.success) {
                email.classList.add('is-invalid');
                existId('invalidEmail').innerText = data.data.message;
            } else {
                resetEmail();
            }

        });

}

/**
 * Email form 만 리셋
 */
function resetEmail() {
    const invalidElem = existId('invalidEmail');
    const elem = existId('email');

    elem.classList.remove('is-invalid');
    invalidElem.innerText = '';

}

/**
 * Form 리셋
 */
function resetInvalid() {
    const signUpInvalidIdArray = ['invalidEmail', 'invalidPassword', 'invalidPasswordCheck', 'invalidName'];
    const signUpIdArray = ['email', 'password', 'passwordCheck', 'name'];

    for (let i = 0; i < signUpInvalidIdArray.length; i++) {
        const invalidElem = existId(signUpInvalidIdArray[i]);
        const elem = existId(signUpIdArray[i]);
        elem.classList.remove('is-invalid');
        invalidElem.innerText = '';
    }

}
