(function () {
    'use strict';
    angular.module('timetableApp.services').service('timetableService',
        ['timetableRepository', 'responseUtil', TimetableService]);

    function TimetableService(timetableRepository, responseUtil) {
        this.create = timetableRepository.create;
        this.getFewMyNotActiveTimetables = getFewMyNotActiveTimetables;
        this.getById = responseUtil.extractData(timetableRepository.getById);

        function getFewMyNotActiveTimetables() {
            return timetableRepository
                .getOwnAndNotActiveTimetables(0)
                .then(response => response.data.content);
        }
    }
})();