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
            },
            VIEW_TIMETABLE: {
                WEEK: 'week'
            },
            MONTH: {
                '1': 'January',
                '2': 'February',
                '3': 'March',
                '4': 'April',
                '5': 'May',
                '6': 'June',
                '7': 'July',
                '8': 'August',
                '9': 'September',
                '10': 'October',
                '11': 'November',
                '12': 'December'
            },
            DAY: {
                '0': 'Sunday',
                '1': 'Monday',
                '2': 'Tuesday',
                '3': 'Wednesday',
                '4': 'Thursday',
                '5': 'Friday',
                '6': 'Saturday'
            },
            EVENT_FORM: {
                TITLE: 'Title:',
                DESCRIPTION: 'Description:',
                LOCATION: 'Where:',
                START_DATE: 'Begins at:',
                END_DATE: 'Ends at:',
                USE_PERIOD: 'Set period',
                PERIOD_UNITS: 'Choose period scale:   ',
                WEEKS: 'weeks',
                DAYS: 'days',
                PERIOD_LENGTH: 'Period:',
                WEEKS_LENGTH: 'week(s)',
                DAYS_LENGTH: 'day(s)'
            },
            CREATE_EVENT_FORM: {
                TITLE: 'Add new event',
                SUBMIT: 'Create'
            },
            FORM: {
                CANCEL: 'Cancel'
            }
        }
    }
})();