(function () {
    angular.module('timetableApp.timetables').service('timetableRepository',
        ['$http', TimetableRepository]);

    function TimetableRepository($http) {
        this.create = create;

        function create(timetable) {
            return $http.post('/timetables', timetable);
        }
    }
})();