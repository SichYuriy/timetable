(function () {
    angular.module('timetableApp.services').service('responseUtil', ResponseUtil);

    function ResponseUtil() {
        this.extractData = extractData;

        function extractData(realFunction) {
            return function() {
                return realFunction.apply(null, arguments)
                    .then(r => r.data);
            }
        }
    }
})();