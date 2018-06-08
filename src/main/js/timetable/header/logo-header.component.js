(function () {
    angular.module('timetableApp.components').component('logoHeader', {
        templateUrl: 'common/header/logo-header.html',
        controller: 'LogoHeaderController',
        controllerAs: 'vm',
        bindings: {
            currentUser: '<'
        }
    })
})();