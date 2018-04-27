angular.module('authentication').factory('authenticationRepository',
    ['$http', function ($http) {
        return new AuthenticationRepository($http);
    }]);

function AuthenticationRepository($http) {
    this.login = login;
    this.logout = logout;
    this.getCurrentUser = getCurrentUser;
    this.register = register;

    function login(user) {
        console.log(user);
        return $http.post('/session/login', user);
    }

    function logout() {
        return $http.post('/session/logout');
    }

    function getCurrentUser() {
        return $http.get('/session/currentUser');
    }

    function register(user) {
        return $http.post('/users/registration', user);
    }
}