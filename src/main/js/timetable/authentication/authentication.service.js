(function () {
    angular.module('timetableApp.services').service('authenticationService',
        ['authenticationRepository', 'responseUtil', AuthenticationService]);

    function AuthenticationService(authenticationRepository, responseUtil) {
        this.login = authenticationRepository.login;
        this.logout = authenticationRepository.logout;
        this.register = authenticationRepository.register;
        this.getCurrentUser = responseUtil.extractData(authenticationRepository.getCurrentUser);
    }
})();
