angular.module('dashboard').controller('userDashboardController',
    ['$scope', 'authenticationService', UserDashboardController]);

function UserDashboardController($scope, authenticationService) {

    authenticationService.getCurrentUser()
        .then(updateCurrentUser, showError);

    function updateCurrentUser(response) {
        $scope.currentUser = response.data;
    }

    function showError() {
        console.log('fail to load current user');
    }
}