(function () {
    angular.module('timetableApp.components').directive('timetableEvent', TimetableEvent);
    
    function TimetableEvent() {
        return {
            link: link
        }
    }
    
    function link(scope, element, attrs) {
        let timetableEvent = scope.$eval(attrs.timetableEvent);
        timetableEvent.startDate = new Date(timetableEvent.startDate);
        timetableEvent.endDate = new Date(timetableEvent.endDate);
        let height =
            ((timetableEvent.endDate.getHours() * 60 + timetableEvent.endDate.getMinutes()) -
            (timetableEvent.startDate.getHours() * 60 + timetableEvent.startDate.getMinutes())) * 50 / 60;
        element.css({
            top: (timetableEvent.startDate.getHours() * 50  + timetableEvent.startDate.getMinutes() * 50 / 60) + 'px',
            height: height + 'px'
        });
    }
})();