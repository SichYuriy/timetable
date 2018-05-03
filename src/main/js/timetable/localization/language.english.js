(function() {
    angular.module('timetableApp')
        .constant('englishTranslations', getTranslations());

    function getTranslations() {
        return {
            LOGIN: 'Sign in',
            REGISTRATION: 'Sign up',
            LOGIN_FORM: {
                TITLE: 'Login',
                LOGIN_FAILED: 'Wrong username or password!',
                USERNAME: 'Username:',
                PASSWORD: 'Password:',
                SUBMIT: 'Submit'
            },
            REGISTRATION_FORM: {
                TITLE: 'Registration',
                USER_EXISTS: 'Sorry! Username is already taken by another user.',
                SUCCESS: 'Success! Registration completed.',
                REPEAT_PASSWORD: 'Repeat Password:',
                SUBMIT: 'Submit',
                DIFFERENT_PASSWORDS: 'Passwords should not be different'
            },
            TIMETABLE_FORM: {
                TITLE: 'Title: ',
                DESCRIPTION: 'Description: ',
                IS_PRIVATE: 'Is private: '
            },
            CREATE_TIMETABLE_FORM: {
                TITLE: 'Create new timetable',
                SUBMIT: 'Save'
            }
        }
    }
})();