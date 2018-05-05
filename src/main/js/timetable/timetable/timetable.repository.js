(function () {
    angular.module('timetableApp.timetables').service('timetableRepository',
        ['$http', TimetableRepository]);

    function TimetableRepository($http) {
        this.create = create;
        this.getOwnAndNotActiveTimetables = getOwnAndNotActiveTimetables;

        function create(timetable) {
            return $http.post('/timetables', timetable);
        }

        function getOwnAndNotActiveTimetables(pageNum) {
            return $http.get('/timetables/ownAndNotActive?pageNum=' + pageNum);
        }
    }
})();