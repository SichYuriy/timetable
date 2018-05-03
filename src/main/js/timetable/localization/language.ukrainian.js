(function() {
    angular.module('timetableApp')
        .constant('ukrainianTranslations', getTranslations());

    function getTranslations() {
        return {
            LOGIN: 'Увійти',
            REGISTRATION: 'Реєстрація',
            LOGIN_FORM: {
                TITLE: 'Вхід',
                LOGIN_FAILED: "Ім'я користувача або пароль неправильний!",
                USERNAME: "Ім'я користувача:",
                PASSWORD: 'Пароль:',
                SUBMIT: 'Увійти'
            },
            REGISTRATION_FORM: {
                TITLE: 'Реєстрація',
                USER_EXISTS: 'Користувач з таким іменем уже зареєстрований.',
                SUCCESS: 'Реєстрація успішно завершена.',
                REPEAT_PASSWORD: 'Пароль ще раз:',
                SUBMIT: 'Зареєструватися',
                DIFFERENT_PASSWORDS: 'Паролі мають співпадати'
            },
            TIMETABLE_FORM: {
                TITLE: 'Назва: ',
                DESCRIPTION: 'Опис: ',
                IS_PRIVATE: 'Приватний: '
            },
            CREATE_TIMETABLE_FORM: {
                TITLE: 'Створити розклад',
                SUBMIT: 'Зберегти'
            }
        }
    }
})();