(function() {
    angular.module('timetableApp')
        .config(['$translateProvider',
            'englishTranslations',
            'ukrainianTranslations',
            localizationConfig]);

    function localizationConfig($translateProvider,
                                englishTranslations,
                                ukrainianTranslations) {
        $translateProvider
            .translations('en', englishTranslations)
            .translations('ua', ukrainianTranslations)
            .useSanitizeValueStrategy('escape')
            .preferredLanguage('ua');
    }
})();