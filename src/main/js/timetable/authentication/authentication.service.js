(function () {
    angular.module('timetableApp.authentication').service('authenticationService',
        ['authenticationRepository', AuthenticationService]);

    function AuthenticationService(authenticationRepository) {
        this.login = authenticationRepository.login;
        this.logout = authenticationRepository.logout;
        this.getCurrentUser = authenticationRepository.getCurrentUser;
        this.register = authenticationRepository.register;
    }
})();
