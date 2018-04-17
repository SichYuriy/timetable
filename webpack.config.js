const path = require('path');
const glob = require('glob');

module.exports = {
    entry: {
        js: glob.sync("./src/main/js/timetable/**/*.js")
    },
    output: {
        filename: 'timetableApp.js',
        path: path.resolve(__dirname, 'target/classes/public')
    }
};