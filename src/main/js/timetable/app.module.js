'use strict';
angular.module('authentication', []);
angular.module('dashboard', ['authentication']);
angular.module('timetableApp', ['ngRoute', 'authentication', 'dashboard']);

angular.module('timetableApp').config(['$locationProvider', '$routeProvider',
    function config($locationProvider, $routeProvider) {
        $locationProvider.hashPrefix('!');

        $routeProvider.when('/login', {
            template: '<login-form></login-form>'
        }).when('/dashboard', {
            template: '',
            controller: 'loadDashboardController'
        }).when('/userDashboard', {
            template: '<user-dashboard></user-dashboard>'
        }).when('/anonymousDashboard', {
            template: '<anonymous-dashboard></anonymous-dashboard>'
        }).when('/registration', {
            template: '<registration-form></registration-form>'
        }).otherwise('/dashboard');
    }
]);

