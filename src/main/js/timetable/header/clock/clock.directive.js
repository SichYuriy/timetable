(function () {
    angular.module('timetableApp.components')
        .directive('clock', ['$interval', 'dateFilter', Clock]);

    function Clock($interval, dateFilter) {
        const TIME_FORMAT = 'hh:mm:ss';

        function link(scope, element) {
            let timeoutId = setInterval(updateTime, 1000);
            element.on('$destroy', stopTimeout);

            function updateTime() {
                element.text(dateFilter(new Date(), TIME_FORMAT));
            }

            function stopTimeout() {
                clearInterval(timeoutId);
            }
        }

        return {
            link: link
        };
    }
})();