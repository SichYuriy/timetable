(function () {
    angular.module('authentication').factory('authenticationService',
        ['authenticationRepository', function (authenticationRepository) {
            return new AuthenticationService(authenticationRepository);
        }]);

    function AuthenticationService(authenticationRepository) {
        this.login = authenticationRepository.login;
        this.logout = authenticationRepository.logout;
        this.getCurrentUser = authenticationRepository.getCurrentUser;
        this.register = authenticationRepository.register;
    }
})();
