(function () {
    angular.module('timetableApp.repositories').service('timetableRepository',
        ['$http', TimetableRepository]);

    function TimetableRepository($http) {
        this.create = create;
        this.getOwnAndNotActiveTimetables = getOwnAndNotActiveTimetables;
        this.getById = getById;

        function create(timetable) {
            return $http.post('/timetables', timetable);
        }

        function getOwnAndNotActiveTimetables(pageNum) {
            return $http.get('/timetables/ownAndNotActive?pageNum=' + pageNum);
        }

        function getById(id) {
            return $http.get('/timetables/' + id);
        }
    }
})();