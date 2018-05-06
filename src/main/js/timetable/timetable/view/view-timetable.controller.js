(function () {
    angular.module('timetableApp.timetables').controller('ViewTimetableController',
        ['$routeParams', 'timetableService', 'authenticationService', ViewTimetableController]);

    function ViewTimetableController($routeParams, timetableService, authenticationService) {
        let vm = this;

        vm.timetable = {};
        vm.currentUser = {};
        vm.days = [];

        activate();

        function activate() {
            timetableService.getById($routeParams.id)
                .then(t => vm.timetable = t);

            authenticationService.getCurrentUser()
                .then(u => vm.currentUser = u);

            vm.days = ['Monday', 'Tuesday', 'Wednesday',
                'Thursday', 'Friday', 'Saturday', 'Sunday'];
        }
    }
})();