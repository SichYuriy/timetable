(function () {
    angular.module('timetableApp.authentication').service('authenticationService',
        ['authenticationRepository', AuthenticationService]);

    function AuthenticationService(authenticationRepository) {
        this.login = authenticationRepository.login;
        this.logout = authenticationRepository.logout;
        this.register = authenticationRepository.register;
        this.getCurrentUser = getCurrentUser;

        function getCurrentUser() {
            return authenticationRepository.getCurrentUser()
                .then(r => r.data);
        }
    }
})();
