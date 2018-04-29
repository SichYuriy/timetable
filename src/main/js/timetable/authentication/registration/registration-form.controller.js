angular.module('authentication').controller('registrationFormController',
    ['$scope', 'authenticationService', RegistrationFormController]);

function RegistrationFormController($scope, authenticationService) {

    $scope.username = '';
    $scope.password = '';
    $scope.repeatPassword = '';

    $scope.registrationSucceed = false;
    $scope.userIsAlreadyExists = false;

    $scope.submitRegistration = submitRegistration;

    function submitRegistration() {
        if ($scope.password !== $scope.repeatPassword) {
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
        $scope.registrationSucceed = false;
        $scope.userIsAlreadyExists = false;
    }

    function clearForm() {
        $scope.username = '';
        $scope.repeatPassword = '';
        $scope.password = '';
    }

    function showRegistrationSucceed()   {
        $scope.registrationSucceed = true;
    }

    function showUserIsAlreadyExists() {
        $scope.userIsAlreadyExists = true;
    }

    function getInputUser() {
        return {
            username: $scope.username,
            password: $scope.password
        }
    }
}