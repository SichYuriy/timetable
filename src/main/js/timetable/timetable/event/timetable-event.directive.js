(function () {
    angular.module('timetableApp.timetables').directive('timetableEvent', TimetableEvent);
    
    function TimetableEvent() {
        return {
            link: link
        }
    }
    
    function link(scope, element, attrs) {
        let timetableEvent = scope.$eval(attrs.timetableEvent);
        console.log(typeof timetableEvent);
        console.log(timetableEvent);
        console.log(timetableEvent.startHour * 50 + 'px');
        element.css({
            position: 'relative',
            top: timetableEvent.startHour * 50 + 'px'
        });
    }
})();