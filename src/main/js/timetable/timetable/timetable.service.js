(function () {
    angular.module('timetableApp.timetables').service('timetableService',
        ['timetableRepository', TimetableService]);

    function TimetableService(timetableRepository) {
        this.create = timetableRepository.create;
    }
})();