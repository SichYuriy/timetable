(function () {
    'use strict';
    angular.module('timetableApp.configs', []);
    angular.module('timetableApp.repositories', ['timetableApp.configs']);
    angular.module('timetableApp.services', ['timetableApp.configs', 'timetableApp.repositories']);
    angular.module('timetableApp.components', ['timetableApp.configs', 'timetableApp.services']);
    angular.module('timetableApp', ['ngRoute', 'timetableApp.configs', 'timetableApp.services',
        'timetableApp.components', 'pascalprecht.translate']);

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
            }).when('/createTimetable', {
                template: '<create-timetable-form></create-timetable-form>'
            }).when('/viewTimetable/:id', {
                template: '<view-timetable></view-timetable>'
            }).when('/editTimetable/:id', {
                template: '<edit-timetable></edit-timetable>'
            }).otherwise('/dashboard');
        }
    ]);
})();