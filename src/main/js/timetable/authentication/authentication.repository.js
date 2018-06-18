(function () {
    angular.module('timetableApp.repositories').service('authenticationRepository',
        ['$http', AuthenticationRepository]);

    function AuthenticationRepository($http) {
        this.login = login;
        this.logout = logout;
        this.getCurrentUser = getCurrentUser;
        this.register = register;

        function login(user) {
            return $http.post('/session/login', user);
        }

        function logout() {
            return $http.post('/session/logout');
        }

        function getCurrentUser() {
            return $http.get('/session/currentUser');
        }

        function register(user) {
            return $http.post('/users', user);
        }
    }
})();