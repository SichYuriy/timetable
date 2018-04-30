(function () {
    angular.module('timetableApp.authentication').controller('RegistrationFormController',
        ['authenticationService', RegistrationFormController]);

    function RegistrationFormController(authenticationService) {
        var vm = this;

        vm.username = '';
        vm.password = '';
        vm.repeatPassword = '';

        vm.alerts = {};

        vm.submitRegistration = submitRegistration;

        function submitRegistration() {
            if (vm.password !== vm.repeatPassword) {
                alert('Different passwords');
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