(function () {
    'use strict';
    angular.module('timetableApp.authentication', []);
    angular.module('timetableApp.dashboard', ['timetableApp.authentication']);
    angular.module('timetableApp', ['ngRoute', 'timetableApp.authentication', 'timetableApp.dashboard', 'pascalprecht.translate']);

    angular.module('timetableApp').config(['$locationProvider', '$routeProvider',
        function config($locationProvider, $routeProvider) {
            $locationProvider.hashPrefix('!');

            $routeProvider.when('/login', {
                template: '<login-form></login-form>'
            }).when('/dashboard', {
                template: '',
                controller: 'LoadDashboardController'
            }).when('/userDashboard', {
                template: '<user-dashboard></user-dashboard>'
            }).when('/anonymousDashboard', {
                template: '<anonymous-dashboard></anonymous-dashboard>'
            }).when('/registration', {
                template: '<registration-form></registration-form>'
            }).otherwise('/dashboard');
        }
    ]);
})();