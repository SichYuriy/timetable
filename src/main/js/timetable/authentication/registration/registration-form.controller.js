(function () {
    angular.module('timetableApp.components').controller('RegistrationFormController',
        ['authenticationService', RegistrationFormController]);

    function RegistrationFormController(authenticationService) {
        let vm = this;

        vm.username = '';
        vm.password = '';
        vm.repeatPassword = '';

        vm.alerts = {};

        vm.submitRegistration = submitRegistration;

        function submitRegistration() {
            if (vm.password !== vm.repeatPassword) {
                vm.alerts.differentPasswords = true;
                return;
            }
            clearPreviousSubmitResults();
            authenticationService.register(getInputUser())
                .then(clearForm)
                .then(showRegistrationSucceed)
                .catch(showUserIsAlreadyExists);
        }

        function clearPreviousSubmitResults() {
            vm.alerts.registrationSucceed = false;
            vm.alerts.userIsAlreadyExists = false;
            vm.alerts.differentPasswords = false;
        }

        function clearForm() {
            vm.username = '';
            vm.repeatPassword = '';
            vm.password = '';
        }

        function showRegistrationSucceed() {
            vm.alerts.registrationSucceed = true;
        }

        function showUserIsAlreadyExists() {
            vm.alerts.userIsAlreadyExists = true;
        }

        function getInputUser() {
            return {
                username: vm.username,
                password: vm.password
            }
        }
    }
})();