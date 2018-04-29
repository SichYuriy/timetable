module.exports = function (config) {
    config.set({

        basePath: './src',

        files: [
            'main/js/bower_components/angular/angular.js',
            'main/js/bower_components/angular-route/angular-route.js',
            'main/js/bower_components/angular-mocks/angular-mocks.js',
            'main/js/timetable/app.module.js',
            'main/js/timetable/**/*!(.module).js',
            'test/js/**/*.spec.js'
        ],

        autoWatch: false,

        frameworks: ['jasmine'],

        browsers: ['Chrome'],

        plugins: [
            'karma-chrome-launcher',
            'karma-jasmine'
        ]
    });
};
