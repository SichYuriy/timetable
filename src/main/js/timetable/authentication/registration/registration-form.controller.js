angular.module('authentication').controller('registrationFormController',
    ['$scope', 'authenticationService', RegistrationFormController]);

function RegistrationFormController($scope, authenticationService) {

    $scope.username = '';
    $scope.password = '';
    $scope.repeatPassword = '';

    $scope.submitRegistration = submitRegistration;

    function submitRegistration() {
        if ($scope.password !== $scope.repeatPassword) {
            alert('Different passwords');
            return;
        }
        authenticationService.register(getInputUser())
            .then(successRegister, failedRegister);
    }

    function successRegister() {
        console.log('successRegister');
    }

    function failedRegister() {
        console.log('failedRegistration');
    }

    function getInputUser() {
        return {
            username: $scope.username,
            password: $scope.password
        }
    }
}