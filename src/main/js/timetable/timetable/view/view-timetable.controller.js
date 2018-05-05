(function () {
    angular.module('timetableApp.timetables').controller('ViewTimetableController',
        ['$routeParams', 'authenticationService', ViewTimetableController]);

    function ViewTimetableController($routeParams, authenticationService) {
        let vm = this;

        vm.timetable = {};
        vm.currentUser = {};

        activate();

        function activate() {
            vm.timetable = {
                id: $routeParams.id,
                title: 'Title TODO:'
            };

            authenticationService.getCurrentUser()
                .then(u => vm.currentUser = u);
        }
    }
})();