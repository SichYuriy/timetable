(function () {
    'use strict';
    angular.module('timetableApp.commonServices', []);
    angular.module('timetableApp.authentication', []);
    angular.module('timetableApp.dashboard', ['timetableApp.authentication']);
    angular.module('timetableApp.timetables', ['timetableApp.authentication', 'timetableApp.commonServices']);
    angular.module('timetableApp', ['ngRoute', 'timetableApp.authentication', 'timetableApp.dashboard',
        'timetableApp.timetables','pascalprecht.translate']);

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