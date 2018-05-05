(function () {
    'use strict';
    angular.module('timetableApp.timetables').service('timetableService',
        ['timetableRepository', TimetableService]);

    function TimetableService(timetableRepository) {
        this.create = timetableRepository.create;
        this.getFewMyNotActiveTimetables = getFewMyNotActiveTimetables;

        function getFewMyNotActiveTimetables() {
            return timetableRepository
                .getOwnAndNotActiveTimetables(0)
                .then(page => page.data.content);
        }
    }
})();